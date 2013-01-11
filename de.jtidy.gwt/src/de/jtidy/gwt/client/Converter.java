package de.jtidy.gwt.client;

import java.util.HashMap;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.jtidy.gwt.client.html2wiki.ConverterService;
import de.jtidy.gwt.client.html2wiki.ConverterServiceAsync;

public class Converter implements EntryPoint {
  private final Label fInputLabel = new Label("Input your sources here:");

  private final TextArea fInputTextArea = new TextArea();

  private final Label fOutputLabel = new Label("Conversion output area:");

  private final VerticalPanel fOutputPanel = new VerticalPanel();

  private boolean codeView = true;

  private final TextArea fOutputTextArea = new TextArea();

  private final HTML fOutputHTML = new HTML();

  private final CheckBox fNoDiv = new CheckBox("Delete DIV tags?");

  private final CheckBox fNoFont = new CheckBox("Delete FONT tags?");

  private final Button fConvertButton = new Button("Convert Input");

  private final Button fClearButton = new Button("Clear");

  private final Button fToggleButton = new Button("Toggle Output View");
  private int fCounter;
  private String fConvertType = "wiki2html";
  private static final int WIKI_INDEX = 3;
  private static final String[] CONVERTER_TYPES_DESCRIPTION = {
      "Wikipedia text to HTML", "Wikipedia text to plain text",
      "HTML to Wikipedia", "HTML to Google Code wiki", "HTML to Trac wiki",
      "HTML to MoinMoin wiki", "Highlight Java", "Highlight PHP",
      "Highlight C#", "Highlight Python", "Highlight XML/HTML",
      "Highlight JavaScript", "Highlight ABAP" };

  private static final String[] CONVERTER_TYPES = { "wiki2html", "wiki2plain",
      "wiki", "googlecode", "trac", "moinmoin", "java", "php", "csharp",
      "python", "xml", "javascript", "abap" };

  private final ListBox fConverterTypeCombo = new ListBox();
  public static ConverterServiceAsync CONVERT_SERVICE;

  public void onModuleLoad() {
    CONVERT_SERVICE = (ConverterServiceAsync) GWT
        .create(ConverterService.class);
    ServiceDefTarget target = (ServiceDefTarget) CONVERT_SERVICE;
    // if (GWT.isScript()) {
    String url = GWT.getModuleBaseURL();
    url = url + "jtidy";
    target.setServiceEntryPoint(url);
    // } else {
    // target.setServiceEntryPoint(GWT.getModuleBaseURL() + "/jtidy");
    // }

    this.fConvertButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        String str = fInputTextArea.getText().trim();
        if (str.length() > 0) {
          runConverter();
        }
        fInputTextArea.setFocus(true);
      }
    });

    this.fConvertButton.setAccessKey('s');

    this.fClearButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        fInputTextArea.setText("");
        fInputTextArea.setFocus(true);
      }
    });

    this.fClearButton.setAccessKey('c');

    this.fToggleButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        codeView = !codeView;
        if (codeView) {
          fOutputLabel.setTitle("Code output view:");
          fOutputPanel.clear();
          fOutputPanel.add(fOutputTextArea);
        } else {
          fOutputLabel.setTitle("HTML output view");
          fOutputPanel.clear();
          fOutputPanel.add(fOutputHTML);
        }
      }
    });

    this.fToggleButton.setAccessKey('t');

    this.fInputTextArea.setCharacterWidth(80);
    this.fInputTextArea.setVisibleLines(10);
    VerticalPanel panel = new VerticalPanel();
    panel.setSpacing(8);
    panel.add(this.fInputLabel);
    panel.add(this.fInputTextArea);

    this.fConverterTypeCombo.setVisibleItemCount(1);
    this.fConverterTypeCombo.addChangeListener(new ChangeListener() {
      public void onChange(Widget sender) {
        int index = fConverterTypeCombo.getSelectedIndex();
        if (index >= 0) {
          if (index <= WIKI_INDEX) {
            fConvertType = CONVERTER_TYPES[index];
            fNoDiv.setVisible(true);
            fNoFont.setVisible(true);
          } else {
            fConvertType = CONVERTER_TYPES[index];
            fNoDiv.setVisible(false);
            fNoFont.setVisible(false);
          }
        }
      }
    });

    for (int i = 0; i < CONVERTER_TYPES_DESCRIPTION.length; ++i)
      this.fConverterTypeCombo.addItem(CONVERTER_TYPES_DESCRIPTION[i]);
    this.fConverterTypeCombo.setSelectedIndex(0);

    this.fNoDiv.setValue(true);
    this.fNoFont.setValue(true);
    HorizontalPanel p1 = new HorizontalPanel();
    p1.setSpacing(4);

    p1.add(this.fConvertButton);
    p1.add(this.fClearButton);
    p1.add(this.fConverterTypeCombo);
    HorizontalPanel p2 = new HorizontalPanel();
    p2.setSpacing(4);
    p2.add(this.fNoDiv);
    p2.add(this.fNoFont);

    panel.add(p1);
    panel.add(p2);

    this.fOutputTextArea.setCharacterWidth(80);
    this.fOutputTextArea.setVisibleLines(10);

    this.fOutputLabel.setVisible(false);
    this.fOutputTextArea.setVisible(false);
    this.fToggleButton.setVisible(false);
    HorizontalPanel p3 = new HorizontalPanel();
    p3.setSpacing(4);
    p3.add(this.fToggleButton);
    p3.add(this.fOutputLabel);
    panel.add(p3);
    this.fOutputPanel.add(this.fOutputTextArea);
    panel.add(this.fOutputPanel);

    RootPanel.get("slot1").add(panel);
    this.fInputTextArea.setFocus(true);
  }

  public void runConverter() {
    HashMap map = new HashMap();
    map.put("converter", this.fConvertType);
    if (this.fNoFont.isChecked()) {
      map.put("no_font", "true");
    }
    if (this.fNoDiv.isChecked()) {
      map.put("no_div", "true");
    }
    this.fOutputTextArea.setText("Loading...");
    this.fOutputLabel.setVisible(true);
    this.fOutputTextArea.setVisible(true);
    this.fOutputHTML.setHTML("<pre></pre>");

    CONVERT_SERVICE.convert(this.fInputTextArea.getText(), map,
        this.fCounter++, new AsyncCallback<String>() {
          public void onSuccess(String resultStr) {
            fOutputTextArea.setText(resultStr);
            fOutputLabel.setVisible(true);
            fOutputTextArea.setVisible(true);
            fToggleButton.setVisible(true);
            fOutputHTML.setHTML("<pre>" + resultStr + "</pre>");
          }

          public void onFailure(Throwable caught) {
            // Convenient way to find out which exception was
            // thrown.
            try {
              // disableLoading();
              throw caught;
            } catch (InvocationException e) {
              // the call didn't complete cleanly
            } catch (Throwable e) {
              // last resort -- a very unexpected exception
            }

          }
        });
  }
}