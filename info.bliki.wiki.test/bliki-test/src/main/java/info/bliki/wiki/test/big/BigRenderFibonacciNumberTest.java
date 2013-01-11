package info.bliki.wiki.test.big;

import info.bliki.wiki.filter.FilterTestSupport;
import junit.framework.Test;
import junit.framework.TestSuite;

public class BigRenderFibonacciNumberTest extends FilterTestSupport {
	public final static String TEST1 = "<sample ci=\"x1:X-value:i:10\" ca=\"Fibonacci[x]:Fibonacci[x1]\" />\r\n" + 
			"\r\n" + 
			"[[Image:FibonacciBlocks.svg|thumb|right|180px|A tiling with squares whose sides are successive Fibonacci numbers in length]]\r\n" + 
			"\r\n" + 
			"[[Image:Fibonacci spiral.svg|right|thumb|A Fibonacci spiral, created by drawing arcs connecting the opposite corners of squares in the Fibonacci tiling shown above; see [[Golden spiral]]]]\r\n" + 
			"\r\n" + 
			"In [[mathematics]], the \'\'\'Fibonacci numbers\'\'\' are a [[sequence]] of numbers named after Leonardo of Pisa, known as [[Fibonacci]], whose \'\'[[Liber Abaci]]\'\' published in 1202 introduced the sequence to Western European mathematics.\r\n" + 
			"\r\n" + 
			"The sequence is defined by the following [[recurrence relation]]:\r\n" + 
			":<math> \r\n" + 
			"  F(n):=  \r\n" + 
			"  \\begin{cases}\r\n" + 
			"    0             & \\mbox{if } n = 0; \\\\\r\n" + 
			"    1             & \\mbox{if } n = 1; \\\\\r\n" + 
			"    F(n-1)+F(n-2) & \\mbox{if } n > 1. \\\\\r\n" + 
			"   \\end{cases}\r\n" + 
			" </math>\r\n" + 
			"\r\n" + 
			"That is, after two starting values, each number is the sum of the two preceding numbers. The first Fibonacci numbers {{OEIS|id=A000045}}, also denoted as \'\'F<sub>n</sub>\'\', for \'\'n\'\'&nbsp;=&nbsp;0,&nbsp;1, ? , are:\r\n" + 
			": 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, ...<ref> By modern convention, the sequence begins with \'\'F\'\'<sub>0</sub>=0. The \'\'Liber Abici\'\' began the sequence with \'\'F\'\'<sub>1</sub> = 1, omitting the initial 0, and the sequence is still written this way by some.</ref>\r\n" + 
			"\r\n" + 
			"The sequence named after Fibonacci was first described in [[Indian mathematics]].<ref>Parmanand Singh. \"Acharya Hemachandra and the (so called) Fibonacci Numbers\". Math. Ed. Siwan, 20(1):28-30, 1986. ISSN 0047-6269]</ref><ref>Parmanand Singh,\"The So-called Fibonacci numbers in ancient and medieval India.\" Historia Mathematica 12(3), 229?44, 1985.</ref>\r\n" + 
			"\r\n" + 
			"The sequence extended to negative index \'\'n\'\' satisfies \'\'F<sub>n</sub>\'\' = \'\'F\'\'<sub>\'\'n\'\'?1</sub> + \'\'F\'\'<sub>\'\'n\'\'?2</sub> for \'\'all\'\' integers \'\'n\'\', and \'\'F<sub>-n</sub>\'\' = (?1)<sup>n+1</sup>\'\'F\'\'<sub>\'\'n\'\'</sub>:\r\n" + 
			"\r\n" + 
			".., -8, 5, -3, 2, -1, 1, followed by the sequence above.\r\n" + 
			"\r\n" + 
			"==Origins==  \r\n" + 
			"The Fibonacci numbers first appeared, under the name \'\'m?tr?meru\'\' (mountain of [[cadence]]), in the work of the [[Sanskrit grammarians|Sanskrit grammarian]] [[Pingala]] (\'\'Chandah-sh?stra\'\', the Art of Prosody, [[450 BC|450]] or [[200 BC]]).  [[Prosody (linguistics)|Prosody]] was important in ancient Indian ritual because of an emphasis on the purity of utterance. The [[Indian mathematicians|Indian mathematician]] [[Virahanka]] (6th century AD) showed how the Fibonacci sequence arose in the analysis of [[Vedic meter|metres]] with long and short syllables. Subsequently, the [[Jain]] philosopher [[Hemachandra]] (c.[[1150]]) composed a well-known text on these.  A commentary on Virahanka\'s work by [[Gopala (mathematician)|Gop?la]] in the 12th century also revisits the problem in some detail.\r\n" + 
			"\r\n" + 
			"Sanskrit vowel sounds can be long (L) or short (S), and Virahanka\'s analysis, which came to be known as \'\'m?tr?-v?tta\'\', wishes to compute how many metres (\'\'m?tr?\'\'s) of a given overall length can be composed of these syllables. If the long syllable is twice as long as the short, the solutions are:\r\n" + 
			": 1 [[mora (linguistics)|mora]]:  S (1 pattern)\r\n" + 
			": 2 morae:  SS; L (2) \r\n" + 
			": 3 morae:  SSS, SL; LS (3)\r\n" + 
			": 4 morae:  SSSS, SSL, SLS; LSS, LL (5)\r\n" + 
			": 5 morae:  SSSSS, SSSL, SSLS, SLSS, SLL; LSSS, LSL, LLS  (8)\r\n" + 
			": 6 morae:  SSSSSS, SSSSL, SSSLS, SSLSS, SLSSS, LSSSS, SSLL, SLSL, SLLS, LSSL, LSLS, LLSS, LLL  (13)\r\n" + 
			": 7 morae:  SSSSSSS, SSSSSL, SSSSLS, SSSLSS, SSLSSS, SLSSSS, LSSSSS, SSSLL, SSLSL, SLSSL, LSSSL, SSLLS, SLSLS, LSSLS, SLLSS, LSLSS, LLSSS, SLLL, LSLL, LLSL, LLLS  (21)\r\n" + 
			"\r\n" + 
			"A pattern of length \'\'n\'\' can be formed by adding S to a pattern of length \'\'n\'\'?1, or L to a pattern of length \'\'n\'\'?2; and the prosodicists showed that the number of patterns of length n is the sum of the two previous numbers in the series.  [[Donald Knuth]] reviews this work in \'\'[[The Art of Computer Programming]]\'\' <!-- see (Vol.&nbsp;1, &sect;1.2.8: Fibonacci Numbers)--> as equivalent formulations of the [[bin packing problem]] for items of lengths 1 and 2. \r\n" + 
			"\r\n" + 
			"In the West, the sequence was first studied by Leonardo of Pisa, known as [[Fibonacci]], in his [[Liber Abaci]] ([[1202]])<ref>{{cite book | title = Fibonacci\'s Liber Abaci | author = Sigler, Laurence E. (trans.) | publisher = Springer-Verlag | year = 2002 | id = ISBN 0-387-95419-8}} Chapter II.12, pp. 404?405.</ref>.  He considers the growth of an idealised (biologically unrealistic) rabbit population, assuming that:\r\n" + 
			"* in the first month there is just one newly-born pair,\r\n" + 
			"* new-born pairs become fertile from their second month on\r\n" + 
			"* each month every fertile pair begets a new pair, and\r\n" + 
			"* the rabbits never die\r\n" + 
			"\r\n" + 
			"Let the population at month \'\'n\'\' be \'\'F\'\'(\'\'n\'\').  At this time, only rabbits who were alive at month \'\'n\'\'?2 are fertile and produce offspring, so \'\'F\'\'(\'\'n\'\'?2) pairs are added to the current population of \'\'F\'\'(\'\'n\'\'?1).  Thus the total is \'\'F\'\'(\'\'n\'\')&nbsp;=&nbsp;\'\'F\'\'(\'\'n\'\'?1)&nbsp;+&nbsp;\'\'F\'\'(\'\'n\'\'?2).<ref>{{cite web\r\n" + 
			"  | last = Knott\r\n" + 
			"  | first = Ron\r\n" + 
			"  | title = Fibonacci\'s Rabbits\r\n" + 
			"  | url=http://www.mcs.surrey.ac.uk/Personal/R.Knott/Fibonacci/fibnat.html#Rabbits\r\n" + 
			"  | publisher =[[University of Surrey]] School of Electronics and Physical Sciences}}</ref>\r\n" + 
			"\r\n" + 
			"==Relation to the golden ratio==\r\n" + 
			"===Closed form expression===\r\n" + 
			"Like every sequence defined by linear [[Recurrence relation|recurrence]], the Fibonacci numbers have a  [[closed-form expression|closed-form solution]]. It has become known as [[Jacques Philippe Marie Binet|Binet]]\'s formula, even though it was already known by [[Abraham de Moivre]]:\r\n" + 
			":<math>F\\left(n\\right) = {{\\varphi^n-(1-\\varphi)^n} \\over {\\sqrt 5}}={{\\varphi^n-(-\\varphi)^{-n}} \\over {\\sqrt 5}}\\, ,</math> where <math>\\varphi</math> is the [[golden ratio]] (note, that <math>1-\\varphi=-1/\\varphi</math> from the defining equation above).\r\n" + 
			"The Fibonacci recursion\r\n" + 
			"\r\n" + 
			":<math>F(n+2)-F(n+1)-F(n)=0\\,</math>\r\n" + 
			"\r\n" + 
			"is similar to the defining equation of the golden ratio in the form\r\n" + 
			"\r\n" + 
			":<math>x^2-x-1=0,\\,</math>\r\n" + 
			"\r\n" + 
			"which is also known as the generating polynomial of the recursion.\r\n" + 
			"\r\n" + 
			"====Proof by [[Mathematical induction|induction]]====\r\n" + 
			"\r\n" + 
			"Any root of the equation above satisfies <math>\\begin{matrix}x^2=x+1,\\end{matrix}\\,</math> and multiplying by <math>x^{n-1}\\,</math> shows:\r\n" + 
			":<math>x^{n+1} = x^n + x^{n-1}\\,</math>\r\n" + 
			"\r\n" + 
			"By definition <math>\\varphi</math> is a root of the equation, and the other root is <math>1-\\varphi=-1/\\varphi\\, .</math>. Therefore:\r\n" + 
			":<math>\\varphi^{n+1}  = \\varphi^n + \\varphi^{n-1}\\, </math>\r\n" + 
			"\r\n" + 
			"and\r\n" + 
			":<math>(1-\\varphi)^{n+1} = (1-\\varphi)^n + (1-\\varphi)^{n-1}\\, .</math>\r\n" + 
			"\r\n" + 
			"Both <math>\\varphi^{n}</math> and <math>(1-\\varphi)^{n}=(-1/\\varphi)^{n}</math>\r\n" + 
			"are [[geometric series]] (for \'\'n\'\' = 1, 2, 3, ...) that satisfy the Fibonacci recursion. The first series grows exponentially; the second exponentially tends to zero, with alternating signs. Because the Fibonacci recursion is linear, any [[linear combination]] of these two series will also satisfy the recursion. These linear combinations form a two-dimensional [[linear vector space]]; the original Fibonacci sequence can be found in this space.\r\n" + 
			"\r\n" + 
			"Linear combinations of series <math>\\varphi^{n}</math> and <math>(1-\\varphi)^{n}</math>, with coefficients \'\'a\'\' and \'\'b\'\', can be defined by\r\n" + 
			":<math>F_{a,b}(n) = a\\varphi^n+b(1-\\varphi)^n</math> for any real <math>a,b\\, .</math>\r\n" + 
			"\r\n" + 
			"All thus-defined series satisfy the Fibonacci recursion\r\n" + 
			":<math>\\begin{align}\r\n" + 
			"  F_{a,b}(n+1) &= a\\varphi^{n+1}+b(1-\\varphi)^{n+1} \\\\\r\n" + 
			"               &=a(\\varphi^{n}+\\varphi^{n-1})+b((1-\\varphi)^{n}+(1-\\varphi)^{n-1}) \\\\\r\n" + 
			"               &=a{\\varphi^{n}+b(1-\\varphi)^{n}}+a{\\varphi^{n-1}+b(1-\\varphi)^{n-1}} \\\\\r\n" + 
			"               &=F_{a,b}(n)+F_{a,b}(n-1)\\,.\r\n" + 
			"\\end{align}</math>\r\n" + 
			"Requiring that <math>F_{a,b}(0)=0</math> and <math>F_{a,b}(1)=1</math> yields <math>a=1/\\sqrt 5</math> and <math>b=-1/\\sqrt 5</math>, resulting in the formula of Binet we started with. It has been shown that this formula satisfies the Fibonacci recursion. Furthermore, an explicit check can be made:\r\n" + 
			":<math>F_{a,b}(0)=\\frac{1}{\\sqrt 5}-\\frac{1}{\\sqrt 5}=0\\,\\!</math>\r\n" + 
			"\r\n" + 
			"and\r\n" + 
			":<math>F_{a,b}(1)=\\frac{\\varphi}{\\sqrt 5}-\\frac{(1-\\varphi)}{\\sqrt 5}=\\frac{-1+2\\varphi}{\\sqrt 5}=\\frac{-1+(1+\\sqrt 5)}{\\sqrt 5}=1,</math>\r\n" + 
			"\r\n" + 
			"establishing the base cases of the induction, proving that\r\n" + 
			":<math>F(n)={{\\varphi^n-(1-\\varphi)^n} \\over {\\sqrt 5}}</math> for all <math> n\\, .</math>\r\n" + 
			"\r\n" + 
			"Therefore, for any two starting values, a combination <math>a,b</math> can be found such that the function <math>F_{a,b}(n)\\,</math> is the exact closed formula for the series.\r\n" + 
			"\r\n" + 
			"====Computation by rounding====\r\n" + 
			"Since <math>\\begin{matrix}|1-\\varphi|^n/\\sqrt 5 < 1/2\\end{matrix}</math> for all <math>n\\geq 0</math>, the number <math>F(n)</math> is the closest integer to <math>\\varphi^n/\\sqrt 5\\, .</math> Therefore it can be found by [[Rounding#Rounding_in_an_exact_computation|rounding]], or in terms of the [[floor function]]:\r\n" + 
			":<math>F(n)=\\bigg\\lfloor\\frac{\\varphi^n}{\\sqrt 5} + \\frac{1}{2}\\bigg\\rfloor.</math>\r\n" + 
			"\r\n" + 
			"===Limit of consecutive quotients===\r\n" + 
			"\r\n" + 
			"[[Johannes Kepler]] observed that the ratio of consecutive Fibonacci numbers converges. He wrote that \"as 5 is to 8 so is 8 to 13, practically, and as 8 is to 13, so is 13 to 21 almost?, and concluded that the limit approaches the golden ratio <math>\\varphi</math>.<ref>{{cite book | last=Kepler | first=Johannes | title=A New Year Gift: On Hexagonal Snow | date=1966 | isbn=0198581203 | publisher=Oxford University Press | pages=92}} Strena seu de Nive Sexangula (1611)</ref>\r\n" + 
			"\r\n" + 
			":<math>\\lim_{n\\to\\infty}\\frac{F(n+1)}{F(n)}=\\varphi,</math>  \r\n" + 
			"This convergence does not depend on the starting values chosen, excluding 0, 0.\r\n" + 
			"\r\n" + 
			"\'\'\'Proof\'\'\':\r\n" + 
			"\r\n" + 
			"It follows from the explicit formula that for any real <math>a \\ne 0, \\, b \\ne 0 \\,</math>\r\n" + 
			":<math>\\begin{align}\r\n" + 
			"  \\lim_{n\\to\\infty}\\frac{F_{a,b}(n+1)}{F_{a,b}(n)}\r\n" + 
			"     &= \\lim_{n\\to\\infty}\\frac{a\\varphi^{n+1}-b(1-\\varphi)^{n+1}}{a\\varphi^n-b(1-\\varphi)^n} \\\\\r\n" + 
			"     &= \\lim_{n\\to\\infty}\\frac{a\\varphi-b(1-\\varphi)(\\frac{1-\\varphi}{\\varphi})^n}{a-b(\\frac{1-\\varphi}{\\varphi})^n} \\\\\r\n" + 
			"     &= \\varphi\r\n" + 
			" \\end{align}</math>\r\n" + 
			"because <math>\\bigl|{\\tfrac{1-\\varphi}{\\varphi}}\\bigr| < 1</math> and thus <math>\\lim_{n\\to\\infty}\\left(\\tfrac{1-\\varphi}{\\varphi}\\right)^n=0 .</math>\r\n" + 
			"\r\n" + 
			"===Decomposition of powers of the golden ratio===\r\n" + 
			"Since the golden ratio satisfies the equation \r\n" + 
			":<math>\\varphi^2=\\varphi+1,\\,</math>\r\n" + 
			"this expression can be used to decompose higher powers <math>\\varphi^n</math> as a linear function of lower powers, which in turn can be decomposed all the way down to a linear combination of <math>\\varphi</math> and 1. The resulting [[recurrence relation]]ships yield Fibonacci numbers as the linear coefficients, thus closing the loop:\r\n" + 
			":<math>\\varphi^n=F(n)\\varphi+F(n-1).</math>\r\n" + 
			"This expression is also true for <math>n \\, <\\, 1 \\, </math> if the Fibonacci sequence <math>F(n) \\,</math> is  [[Generalizations_of_Fibonacci_numbers#Extension_to_negative_integers|extended to negative integers]] using the Fibonacci rule <math>F(n) = F(n-1) + F(n-2) . \\, </math>\r\n" + 
			"\r\n" + 
			"==Matrix form==\r\n" + 
			"\r\n" + 
			"A 2-dimensional system of linear [[difference equations]] that describes the Fibonacci sequence is\r\n" + 
			":<math>{F_{k+2} \\choose F_{k+1}} = \\begin{pmatrix} 1 & 1 \\\\ 1 & 0 \\end{pmatrix} {F_{k+1} \\choose F_{k}}</math>\r\n" + 
			"\r\n" + 
			"or\r\n" + 
			":<math>\\vec F_{k+1} = A \\vec F_{k}.\\,</math>\r\n" + 
			"\r\n" + 
			"The [[eigenvalue]]s of the matrix A are <math>\\varphi\\,\\!</math> and <math>(1-\\varphi)\\,\\!</math>, and the elements of the [[eigenvector]]s of A, <math>{\\varphi \\choose 1}</math> and <math>{1 \\choose -\\varphi}</math>, are in the ratios <math>\\varphi\\,\\!</math> and <math>(1-\\varphi\\,\\!)</math>.\r\n" + 
			"\r\n" + 
			"This matrix has a determinant of &minus;1, and thus it is a 2&times;2 [[unimodular matrix]].  This property can be understood in terms of the [[continued fraction]] representation for the golden ratio: \r\n" + 
			":<math>\\varphi\r\n" + 
			"=1 + \\cfrac{1}{1 + \\cfrac{1}{1 + \\cfrac{1}{\\;\\;\\ddots\\,}}} \\;. </math> \r\n" + 
			"The Fibonacci numbers occur as the ratio of successive  convergents of the continued fraction for <math>\\varphi\\,\\!</math>, and the matrix formed from successive convergents of any continued fraction has a determinant of +1 or &minus;1.\r\n" + 
			"\r\n" + 
			"The matrix representation gives the following [[closed expression]] for the Fibonacci numbers:\r\n" + 
			":<math>\\begin{pmatrix} 1 & 1 \\\\ 1 & 0 \\end{pmatrix}^n =\r\n" + 
			"       \\begin{pmatrix} F_{n+1} & F_n \\\\\r\n" + 
			"                       F_n     & F_{n-1} \\end{pmatrix}.\r\n" + 
			"</math>\r\n" + 
			"\r\n" + 
			"Taking the determinant of both sides of this equation yields [[Cassini\'s identity]]\r\n" + 
			":<math> F_{n+1}F_{n-1} - F_n^2 = (-1)^n.\\,</math>\r\n" + 
			"\r\n" + 
			"Additionally, since <math> A^n A^m=A^{m+n}</math> for any square matrix <math>A</math>, the following identities can be derived:\r\n" + 
			":<math>{F_n}^2 + {F_{n-1}}^2 = F_{2n-1},\\,</math>\r\n" + 
			":<math>F_{n+1}F_{m} + F_n F_{m-1} = F_{m+n}.\\, </math>\r\n" + 
			"\r\n" + 
			"==Recognizing Fibonacci numbers==\r\n" + 
			"\r\n" + 
			"Occasionally, the question may arise whether a positive integer <math>z</math> is a Fibonacci number. Since <math>F(n)</math> is the closest integer to <math>\\varphi^n/\\sqrt{5}</math>, the most straightforward, brute-force test is the identity\r\n" + 
			":<math>F\\bigg(\\bigg\\lfloor\\log_\\varphi(\\sqrt{5}z)+\\frac{1}{2}\\bigg\\rfloor\\bigg)=z,</math>\r\n" + 
			"which is true [[if and only if]] <math>z</math> is a Fibonacci number.\r\n" + 
			"\r\n" + 
			"Alternatively, an elegant algebraic test states, that a positive integer <math>z</math> is a Fibonacci number if (and only if) <math>\\bigg(5z^2+4\\bigg)</math> or <math>\\bigg(5z^2-4\\bigg)</math> is a [[perfect square]].<ref>{{cite book | last=Posamentier | first=Alfred | coauthors = Lehmann, Ingmar| title=The (Fabulous) FIBONACCI Numbers | date=2007 | isbn=978-1-59102-475-0 | publisher=Prometheus Books | pages=305}}</ref> \r\n" + 
			"\r\n" + 
			"A slightly more sophisticated test uses the fact that the [[convergent (continued fraction)|convergent]]s of the [[continued fraction]] representation of <math>\\varphi</math> are ratios of successive Fibonacci numbers, that is the inequality\r\n" + 
			":<math>\\bigg|\\varphi-\\frac{p}{q}\\bigg|<\\frac{1}{q^2}</math>\r\n" + 
			"(with [[coprime]] positive integers <math>p</math>, <math>q</math>) is true if and only if <math>p</math> and <math>q</math> are successive Fibonacci numbers. From this one derives the criterion that <math>z</math> is a Fibonacci number if and only if the [[closed interval]]\r\n" + 
			":<math>\\bigg[\\varphi z-\\frac{1}{z},\\varphi z+\\frac{1}{z}\\bigg]</math>\r\n" + 
			"contains a positive integer.<ref>M.&nbsp;Möbius, \'\'Wie erkennt man eine Fibonacci Zahl?\'\', Math. Semesterber. (1998) 45; 243?246</ref>\r\n" + 
			"\r\n" + 
			"==Identities==\r\n" + 
			"#\'\'F\'\'(\'\'n\'\' + 1) = \'\'F\'\'(\'\'n\'\') + \'\'F\'\'(\'\'n\'\' &minus; 1)\r\n" + 
			"#\'\'F\'\'(0) + \'\'F\'\'(1) + \'\'F\'\'(2) + ? + \'\'F\'\'(\'\'n\'\') = \'\'F\'\'(\'\'n\'\' + 2) &minus; 1\r\n" + 
			"#\'\'F\'\'(1) + 2 \'\'F\'\'(2) + 3 \'\'F\'\'(3) + ? + \'\'n F\'\'(\'\'n\'\') = \'\'n F\'\'(\'\'n\'\' + 2) &minus; \'\'F\'\'(\'\'n\'\' + 3) + 2\r\n" + 
			"#\'\'F\'\'(0)² + \'\'F\'\'(1)² + \'\'F\'\'(2)² + ? + \'\'F\'\'(\'\'n\'\')² = \'\'F\'\'(\'\'n\'\')  \'\'F\'\'(\'\'n\'\' + 1)\r\n" + 
			"\r\n" + 
			"These identities can be proven using many different methods.\r\n" + 
			"But, among all, we wish to present an elegant proof for each of them using [[combinatorial proof|combinatorial arguments]] here.\r\n" + 
			"In particular, \'\'F\'\'(\'\'n\'\') can be interpreted as the number of ways summing 1\'s and 2\'s to \'\'n\'\' &minus; 1, with the convention that \'\'F\'\'(0) = 0, meaning no sum will add up to &minus;1, and that \'\'F\'\'(1) = 1, meaning the empty sum will \"add up\" to 0.\r\n" + 
			"Here the order of the summands matters.\r\n" + 
			"For example, 1 + 2 and 2 + 1 are considered two different sums and are counted twice.\r\n" + 
			"\r\n" + 
			"=== Proof of the first identity ===\r\n" + 
			"[[Without loss of generality]], we may assume \'\'n\'\' ? 1.\r\n" + 
			"Then \'\'F\'\'(\'\'n\'\' + 1) counts the number of ways summing 1\'s and 2\'s to \'\'n\'\'.\r\n" + 
			"\r\n" + 
			"When the first summand is 1, there are \'\'F\'\'(\'\'n\'\') ways to complete the counting for \'\'n\'\' &minus; 1; and when the first summand is 2, there are \'\'F\'\'(\'\'n\'\' &minus; 1) ways to complete the counting for \'\'n\'\' &minus; 2.\r\n" + 
			"Thus, in total, there are \'\'F\'\'(\'\'n\'\') + \'\'F\'\'(\'\'n\'\' &minus; 1) ways to complete the counting for \'\'n\'\'.\r\n" + 
			"\r\n" + 
			"=== Proof of the second identity ===\r\n" + 
			"We count the number of ways summing 1\'s and 2\'s to \'\'n\'\' + 1 such that at least one of the summands is 2.\r\n" + 
			"\r\n" + 
			"As before, there are \'\'F\'\'(\'\'n\'\' + 2) ways summing 1\'s and 2\'s to \'\'n\'\' + 1 when \'\'n\'\' ? 0.\r\n" + 
			"Since there is only one sum of \'\'n\'\' + 1 that does not use any 2, namely 1 + ? + 1 (\'\'n\'\' + 1 terms), we subtract 1 from \'\'F\'\'(\'\'n\'\' + 2).\r\n" + 
			"\r\n" + 
			"Equivalently, we can consider the first occurrence of 2 as a summand.\r\n" + 
			"If, in a sum, the first summand is 2, then there are \'\'F\'\'(\'\'n\'\') ways to the complete the counting for \'\'n\'\' &minus; 1.\r\n" + 
			"If the second summand is 2 but the first is 1, then there are \'\'F\'\'(\'\'n\'\' &minus; 1) ways to complete the counting for \'\'n\'\' &minus; 2.\r\n" + 
			"Proceed in this fashion.\r\n" + 
			"Eventually we consider the (\'\'n\'\' + 1)th summand.\r\n" + 
			"If it is 2 but all of the previous \'\'n\'\' summands are 1\'s, then there are \'\'F\'\'(0) ways to complete the counting for 0.\r\n" + 
			"If a sum contains 2 as a summand, the first occurrence of such summand must take place in between the first and (\'\'n\'\' + 1)th position.\r\n" + 
			"Thus \'\'F\'\'(\'\'n\'\') + \'\'F\'\'(\'\'n\'\' &minus; 1) + ? + \'\'F\'\'(0) gives the desired counting.\r\n" + 
			"\r\n" + 
			"=== Proof of the third identity ===\r\n" + 
			"This identity can be established in two stages.\r\n" + 
			"First, we count the number of ways summing 1s and 2s to &minus;1, 0, ?, or \'\'n\'\' + 1 such that at least one of the summands is 2.\r\n" + 
			"\r\n" + 
			"By our second identity, there are \'\'F\'\'(\'\'n\'\' + 2) &minus;  1 ways summing to \'\'n\'\' + 1; \'\'F\'\'(\'\'n\'\' + 1) &minus; 1 ways summing to \'\'n\'\'; ?; and, eventually, \'\'F\'\'(2) &minus; 1 way summing to 1.\r\n" + 
			"As \'\'F\'\'(1) &minus; 1 = \'\'F\'\'(0) = 0, we can add up all \'\'n\'\' + 1 sums and apply the second identity again to obtain\r\n" + 
			": &nbsp;&nbsp;&nbsp;[\'\'F\'\'(\'\'n\'\' + 2) &minus; 1] + [\'\'F\'\'(\'\'n\'\' + 1) &minus; 1] + ? + [\'\'F\'\'(2) &minus; 1]\r\n" + 
			": = [\'\'F\'\'(\'\'n\'\' + 2) &minus; 1] + [\'\'F\'\'(\'\'n\'\' + 1) &minus; 1] + ? + [\'\'F\'\'(2) &minus; 1] + [\'\'F\'\'(1) &minus; 1] + \'\'F\'\'(0)\r\n" + 
			": = \'\'F\'\'(\'\'n\'\' + 2) + [\'\'F\'\'(\'\'n\'\' + 1) + ? + \'\'F\'\'(1) + \'\'F\'\'(0)] &minus; (\'\'n\'\' + 2)\r\n" + 
			": = \'\'F\'\'(\'\'n\'\' + 2) + \'\'F\'\'(\'\'n\'\' + 3) &minus; (\'\'n\'\' + 2).\r\n" + 
			"\r\n" + 
			"On the other hand, we observe from the second identity that there are\r\n" + 
			"* \'\'F\'\'(0) + \'\'F\'\'(1) + ? + \'\'F\'\'(\'\'n\'\' &minus; 1) + \'\'F\'\'(\'\'n\'\') ways summing to \'\'n\'\' + 1;\r\n" + 
			"* \'\'F\'\'(0) + \'\'F\'\'(1) + ? + \'\'F\'\'(\'\'n\'\' &minus; 1) ways summing to \'\'n\'\';\r\n" + 
			"??\r\n" + 
			"* \'\'F\'\'(0) way summing to &minus;1.\r\n" + 
			"Adding up all \'\'n\'\' + 1 sums, we see that there are\r\n" + 
			"* (\'\'n\'\' + 1) \'\'F\'\'(0) + \'\'n\'\' \'\'F\'\'(1) + ? + \'\'F\'\'(\'\'n\'\') ways summing to &minus;1, 0, ?, or \'\'n\'\' + 1.\r\n" + 
			"\r\n" + 
			"Since the two methods of counting refer to the same number, we have\r\n" + 
			": (\'\'n\'\' + 1) \'\'F\'\'(0) + \'\'n\'\' \'\'F\'\'(1) + ? + \'\'F\'\'(\'\'n\'\') = \'\'F\'\'(\'\'n\'\' + 2) + \'\'F\'\'(\'\'n\'\' + 3) &minus; (\'\'n\'\' + 2)\r\n" + 
			"\r\n" + 
			"Finally, we complete the proof by subtracting the above identity from \'\'n\'\' + 1 times the second identity.\r\n" + 
			"\r\n" + 
			"===Identity for doubling \'\'n\'\'===\r\n" + 
			"There is a very simple formula for doubling \'\'n\'\' :<math>F_{2n} = F_{n+1}^2 - F_{n-1}^2 = F_n(F_{n+1}+F_{n-1}) </math>. \r\n" + 
			"<ref>http://mathworld.wolfram.com/FibonacciNumber.html</ref>\r\n" + 
			"\r\n" + 
			"Another identity useful for calculating \'\'F<sub>n</sub>\'\' for large values of \'\'n\'\' is\r\n" + 
			":<math>F_{2n+k} = F_k F_{n+1}^2 + 2 F_{k-1} F_{n+1} F_n + F_{k-2} F_n^2 </math>\r\n" + 
			"\r\n" + 
			"for all integers \'\'n\'\' and \'\'k\'\'. [[Dijkstra]]<ref>E. W. Dijkstra (1978). \'\'In honour of Fibonacci.\'\' [http://www.cs.utexas.edu/users/EWD/ewd06xx/EWD654.PDF Report EWD654].</ref> points out that doubling identities of this type can be used to calculate \'\'F<sub>n</sub>\'\' using O(log \'\'n\'\') arithmetic operations. Notice that, with the definition of Fibonacci numbers with negative n given in the introduction, this formula reduces to the double n formula when k = 0.\r\n" + 
			"\r\n" + 
			"(From practical standpoint it should be noticed that the calculation involves manipulation of numbers which length (number of digits) is <math>{\\rm \\Theta}(n)\\,</math>. Thus the actual performance depends mainly upon efficiency of the implemented [[multiplication algorithm| long multiplication]], and usually is <math>{\\rm \\Theta}(n \\,\\log n)</math> or <math>{\\rm \\Theta}(n ^{\\log_2 3})</math>.)\r\n" + 
			"\r\n" + 
			"===Other identities===\r\n" + 
			"\r\n" + 
			"Other identities include relationships to the [[Lucas number]]s, which have the same recursive properties but start with \'\'L\'\'<sub>\'\'0\'\'</sub>=2 and \'\'L\'\'<sub>\'\'1\'\'</sub>=1. These properties include\r\n" + 
			"\'\'F\'\'<sub>\'\'2n\'\'</sub>=\'\'F\'\'<sub>\'\'n\'\'</sub>\'\'L\'\'<sub>\'\'n\'\'</sub>.\r\n" + 
			"\r\n" + 
			"There are also scaling identities, which take you from \'\'F\'\'<sub>n</sub> and \'\'F\'\'<sub>n+1</sub> to a variety of things of the form \'\'F\'\'<sub>an+b</sub>; for instance\r\n" + 
			"\r\n" + 
			"<math>F_{3n} = 2F_n^3 + 3F_n F_{n+1} F_{n-1} = 5F_{n}^3 + 3 (-1)^n F_{n} </math> by Cassini\'s identity.\r\n" + 
			"\r\n" + 
			"<math>F_{3n+1} = F_{n+1}^3 + 3 F_{n+1}F_n^2 - F_n^3</math>\r\n" + 
			"\r\n" + 
			"<math>F_{3n+2} = F_{n+1}^3 + 3 F_{n+1}^2F_n + F_n^3</math>\r\n" + 
			"\r\n" + 
			"<math>F_{4n} = 4F_nF_{n+1}(F_{n+1}^2 + 2F_n^2) - 3F_n^2(F_n^2 + 2F_{n+1}^2)</math>\r\n" + 
			"\r\n" + 
			"These can be found experimentally using [[lattice reduction]], and are useful in setting up the [[special number field sieve]] to [[Factorization|factorize]] a Fibonacci number. Such relations exist in a very general sense for numbers defined by recurrence relations, see the section on multiplication formulae under [[Perrin number]]s for details.\r\n" + 
			"\r\n" + 
			"==Power series==\r\n" + 
			"The [[generating function]] of the Fibonacci sequence is the [[power series]]\r\n" + 
			":<math>s(x)=\\sum_{k=0}^{\\infty} F_k x^k.</math>\r\n" + 
			"\r\n" + 
			"This series has a simple and interesting closed-form solution for \'\'x\'\' < 1/<math>\\varphi</math>\r\n" + 
			":<math>s(x)=\\frac{x}{1-x-x^2}.</math>\r\n" + 
			"\r\n" + 
			"This solution can be proven by using the Fibonacci recurrence to expand each coefficient in the infinite sum defining <math>s(x)</math>:\r\n" + 
			":<math>\\begin{align}\r\n" + 
			"  s(x) &= \\sum_{k=0}^{\\infty} F_k x^k \\\\\r\n" + 
			"       &= F_0 + F_1x + \\sum_{k=2}^{\\infty} \\left( F_{k-1} + F_{k-2} \\right) x^k \\\\\r\n" + 
			"       &= x + \\sum_{k=2}^{\\infty} F_{k-1} x^k + \\sum_{k=2}^{\\infty} F_{k-2} x^k \\\\\r\n" + 
			"       &= x + x\\sum_{k=0}^{\\infty} F_k x^k + x^2\\sum_{k=0}^{\\infty} F_k x^k \\\\\r\n" + 
			"       &= x + x s(x) + x^2 s(x)\r\n" + 
			"  \\end{align}</math>\r\n" + 
			"\r\n" + 
			"Solving the equation <math>s(x)=x+xs(x)+x^2s(x)</math> for <math>s(x)</math> results in the closed form solution.\r\n" + 
			"\r\n" + 
			"In particular, math puzzle-books note the curious value <math>\\frac{s(\\frac{1}{10})}{10}=\\frac{1}{89}</math>, or more generally\r\n" + 
			"\r\n" + 
			":<math>\\sum_{n = 1}^{\\infty}{\\frac {F(n)}{10^{(k + 1)(n + 1)}}} = \\frac {1}{10^{2k + 2} - 10^{k + 1} - 1}</math>\r\n" + 
			"\r\n" + 
			"for all integers <math>k >= 0</math>.\r\n" + 
			"\r\n" + 
			"Conversely,\r\n" + 
			":<math>\\sum_{n=0}^\\infty\\,\\frac{F_n}{k^{n}}\\,=\\,\\frac{k}{k^{2}-k-1}.</math>\r\n" + 
			"\r\n" + 
			"==Reciprocal sums==\r\n" + 
			"\r\n" + 
			"<!--\r\n" + 
			"{{cite book\r\n" + 
			"  | last =Borwein\r\n" + 
			"  | first =Jonathan M.\r\n" + 
			"  | authorlink =Jonathan Borwein\r\n" + 
			"  | coauthors =[[Peter Borwein|Peter B. Borwein]]\r\n" + 
			"  | title =Pi and the AGM: A Study in Analytic Number Theory and Computational Complexity\r\n" + 
			"  | pages =91?101\r\n" + 
			"  | publisher =Wiley\r\n" + 
			"  | year =1998\r\n" + 
			"  | month =July\r\n" + 
			"  | url =http://www.wiley.com/WileyCDA/WileyTitle/productCd-047131515X.html\r\n" + 
			"  | id = ISBN 978-0-471-31515-5 }}\r\n" + 
			"It credits some formulae to {{cite journal | author = Landau, E. | title = Sur la Série des Invers de Nombres de Fibonacci | journal = Bull. Soc. Math. France | volume = 27 | year = 1899 | pages = 298?300}}\r\n" + 
			"-->\r\n" + 
			"Infinite sums over reciprocal Fibonacci numbers can sometimes be evaluated in terms of [[theta function]]s. For example, we can write the sum of every odd-indexed reciprocal Fibonacci number as\r\n" + 
			":<math>\\sum_{k=0}^\\infty \\frac{1}{F_{2k+1}} = \\frac{\\sqrt{5}}{4}\\vartheta_2^2 \\left(0, \\frac{3-\\sqrt 5}{2}\\right) ,</math>\r\n" + 
			"\r\n" + 
			"and the sum of squared reciprocal Fibonacci numbers as\r\n" + 
			":<math>\\sum_{k=1}^\\infty \\frac{1}{F_k^2} = \\frac{5}{24} \\left(\\vartheta_2^4\\left(0, \\frac{3-\\sqrt 5}{2}\\right) - \\vartheta_4^4\\left(0, \\frac{3-\\sqrt 5}{2}\\right) + 1 \\right).</math>\r\n" + 
			"\r\n" + 
			"If we add 1 to each Fibonacci number in the first sum, there is also the closed form\r\n" + 
			":<math>\\sum_{k=0}^\\infty \\frac{1}{1+F_{2k+1}} = \\frac{\\sqrt{5}}{2},</math>\r\n" + 
			"\r\n" + 
			"and there is a nice \'\'nested\'\' sum of squared Fibonacci numbers giving the reciprocal of the [[golden ratio]],\r\n" + 
			":<math>\\sum_{k=1}^\\infty \\frac{(-1)^{k+1}}{\\sum_{j=1}^k {F_{j}}^2} = \\frac{\\sqrt{5}-1}{2}.</math>\r\n" + 
			"\r\n" + 
			"Results such as these make it plausible that a closed formula for the plain sum of reciprocal Fibonacci numbers could be found, but none is yet known. Despite that, the [[reciprocal Fibonacci constant]]\r\n" + 
			":<math>\\psi = \\sum_{k=1}^{\\infty} \\frac{1}{F_k} = 3.359885666243 \\dots</math> \r\n" + 
			"\r\n" + 
			"has been proved [[irrational number|irrational]] by [[Richard André-Jeannin]].\r\n" + 
			"\r\n" + 
			"==Primes and divisibility==\r\n" + 
			"{{main|Fibonacci prime}}\r\n" + 
			"A \'\'\'Fibonacci prime\'\'\' is a Fibonacci number that is [[prime number|prime]] {{OEIS|id=A005478}}. The first few are:\r\n" + 
			": 2, 3, 5, 13, 89, 233, 1597, 28657, 514229, ?\r\n" + 
			"Fibonacci primes with thousands of digits have been found, but it is not known whether there are infinitely many. They must all have a prime index, except \'\'F\'\'<sub>4</sub> = 3.\r\n" + 
			"\r\n" + 
			"Any three consecutive Fibonacci numbers, taken two at a time, are [[relatively prime]]: that is,\r\n" + 
			":[[greatest common divisor|gcd]](\'\'F\'\'<sub>\'\'n\'\'</sub>,\'\'F\'\'<sub>\'\'n\'\'+1</sub>) = gcd(\'\'F\'\'<sub>\'\'n\'\'</sub>,\'\'F\'\'<sub>\'\'n\'\'+2</sub>) = 1.\r\n" + 
			"More generally,\r\n" + 
			":gcd(\'\'F\'\'<sub>\'\'n\'\'</sub>, \'\'F\'\'<sub>\'\'m\'\'</sub>) = \'\'F\'\'<sub>gcd(\'\'n\'\',\'\'m\'\').</sub><ref>[[Paulo Ribenboim]], \'\'My Numbers, My Friends\'\', Springer-Verlag 2000</ref>\r\n" + 
			"\r\n" + 
			"A proof of this striking fact is online at [http://www.math.hmc.edu/funfacts/ffiles/20004.5.shtml Harvey Mudd College\'s Fun Math site]\r\n" + 
			"\r\n" + 
			"==Right triangles==\r\n" + 
			"Starting with 5, every second Fibonacci number is the length of the hypotenuse of a right triangle with integer sides, or in other words, the largest number in a [[Pythagorean triple]].  The length of the longer leg of this triangle is equal to the sum of the three sides of the preceding triangle in this series of triangles, and the shorter leg is equal to the difference between the preceding bypassed Fibonacci number and the shorter leg of the preceding triangle.\r\n" + 
			"\r\n" + 
			"The first triangle in this series has sides of length 5, 4, and 3. Skipping 8, the next triangle has sides of length 13, 12 (5&nbsp;+&nbsp;4&nbsp;+&nbsp;3), and 5 (8&nbsp;&minus;&nbsp;3). Skipping 21, the next triangle has sides of length 34, 30 (13&nbsp;+&nbsp;12&nbsp;+&nbsp;5), and 16 (21&nbsp;&minus;&nbsp;5). This series continues indefinitely.\r\n" + 
			"\r\n" + 
			"==Magnitude of Fibonacci numbers==\r\n" + 
			"Since <math>F_n</math> is [[asymptotic]] to <math>\\varphi^n/\\sqrt5</math>, the number of digits in the base \'\'b\'\' representation of <math>F_n\\,</math> is asymptotic to <math>n\\,\\log_b\\varphi</math>.\r\n" + 
			"\r\n" + 
			"In base 10, for every integer greater than 1 there are 4 or 5 Fibonacci numbers with that number of digits, in most cases 5.\r\n" + 
			"\r\n" + 
			"==Applications==\r\n" + 
			"\r\n" + 
			"The Fibonacci numbers are important in the run-time analysis of [[Euclidean algorithm|Euclid\'s algorithm]] to determine the [[greatest common divisor]] of two integers: the worst case input for this algorithm is a pair of consecutive Fibonacci numbers.\r\n" + 
			"\r\n" + 
			"[[Yuri Matiyasevich]] was able to show that the Fibonacci numbers can be defined by a [[Diophantine equation]], which led to [[Matiyasevich\'s theorem|his original solution]] of [[Hilbert\'s tenth problem]].\r\n" + 
			"\r\n" + 
			"The Fibonacci numbers occur in the sums of \"shallow\" diagonals in [[Pascal\'s triangle]] and [[Lozani?\'s triangle]] (\'\'see \"[[Binomial coefficient]]\"\'\').\r\n" + 
			"\r\n" + 
			"Every positive integer can be written in a unique way as the sum of \'\'one or more\'\' distinct Fibonacci numbers in such a way that the sum does not include any two consecutive Fibonacci numbers. This is known as [[Zeckendorf\'s theorem]], and a sum of Fibonacci numbers that satisfies these conditions is called a Zeckendorf representation.\r\n" + 
			"\r\n" + 
			"Fibonacci numbers are used by some [[pseudorandom number generators]].<!-- Knuth vol. 2 -->\r\n" + 
			"\r\n" + 
			"Fibonacci numbers arise in the analysis of the [[Fibonacci heap]] data structure. \r\n" + 
			"\r\n" + 
			"A one-dimensional optimization method, called the [[Fibonacci search technique]], uses Fibonacci numbers.<ref>{{cite journal | author=M. Avriel and D.J. Wilde | title=Optimality of the Symmetric Fibonacci Search Technique | journal=[[Fibonacci Quarterly]] | year=1966 | issue=3 | pages= 265&mdash;269}}</ref>\r\n" + 
			"\r\n" + 
			"In [[music]], Fibonacci numbers are sometimes used to determine tunings, and, as in visual art, to determine the length or size of [[content]] or [[form (music)|formal]] elements. It is commonly thought that the first movement of [[Béla Bartók]]\'s \'\'[[Music for Strings, Percussion, and Celesta]]\'\' was structured using Fibonacci numbers.\r\n" + 
			"\r\n" + 
			"Since the [[conversion of units|conversion]] factor 1.609344 for [[mile]]s to kilometers is close to the [[golden ratio]] (denoted ?), the decomposition of distance in miles into a sum of Fibonacci numbers becomes nearly the kilometer sum when the Fibonacci numbers are replaced by their successors. This method amounts to a [[radix]] 2 [[Fibonacci coding|number]] [[processor register|register]] in [[golden ratio base]] ? being shifted. To convert from kilometers to miles, shift the register down the Fibonacci sequence instead.<ref>[http://www.mcs.surrey.ac.uk/Personal/R.Knott/Fibonacci/fibrep.html#kilos An Application of the Fibonacci Number Representation]</ref><ref>[http://people.bath.ac.uk/pst20/fibonacci.html#Sequence A Practical Use of the Sequence]</ref><ref>[http://eom.springer.de/Z/z120020.htm Zeckendorf representation]</ref>\r\n" + 
			"\r\n" + 
			"==Fibonacci numbers in nature==\r\n" + 
			"[[Image:Helianthus whorl.jpg|thumb|[[Sunflower]] head displaying florets in spirals of 34 and 55 around the outside]]\r\n" + 
			"Fibonacci sequences appear in biological settings,<ref>{{cite journal | author=S. Douady and Y. Couder | title=Phyllotaxis as a Dynamical Self Organizing Process | journal=Journal of Theoretical Biology | year=1996 | issue=178 | pages= 255&ndash;274 | url=http://www.math.ntnu.no/~jarlet/Douady96.pdf }}</ref> such as branching in trees, the fruitlets of a [[pineapple]],<ref>{{cite book|first=Judy|last=Jones|coauthors=William Wilson|title=An Incomplete Education|publisher=Ballantine Books|year=2006|id=ISBN 978-0-7394-7582-9|pages=544|chapter=Science}}</ref> an uncurling fern and the arrangement of  a [[pine cone]].<ref>{{cite journal | author=A. Brousseau | title=Fibonacci Statistics in Conifers | journal=[[Fibonacci Quarterly]] | year=1969 | issue=7 | pages= 525&mdash;532}}</ref>. In addition, numerous poorly substantiated claims of Fibonacci numbers or [[golden section]]s in nature are found in popular sources, e.g. relating to the breeding of rabbits, the spirals of shells, and the curve of waves{{Fact|date=February 2007}}.\r\n" + 
			"\r\n" + 
			"[[Przemyslaw Prusinkiewicz]] advanced the idea that real instances can be in part understood as the expression of certain algebraic constraints on [[free group]]s, specifically as certain [[L-system|Lindenmayer grammar]]s.<ref>{{cite book|first=Przemyslaw|last=Prusinkiewicz|coauthors=James Hanan|title=Lindenmayer Systems, Fractals, and Plants (Lecture Notes in Biomathematics)|publisher=[[Springer Science+Business Media|Springer-Verlag]]|year=1989|id=ISBN 0-387-97092-4}}</ref>\r\n" + 
			"\r\n" + 
			"A model for the pattern of [[floret]]s in the head of a [[sunflower]] was proposed by H. Vogel in 1979.<ref>\r\n" + 
			"{{Citation\r\n" + 
			"  | last =Vogel\r\n" + 
			"  | first =H\r\n" + 
			"  | title =A better way to construct the sunflower head\r\n" + 
			"  | journal =Mathematical Biosciences\r\n" + 
			"  | issue =44\r\n" + 
			"  | pages =179?189\r\n" + 
			"  | year =1979\r\n" + 
			"}}</ref>\r\n" + 
			"This has the form\r\n" + 
			":<math>\\theta = \\frac{2\\pi}{\\phi^2} n</math>, <math>r = c \\sqrt{n}</math>\r\n" + 
			"where \'\'n\'\' is the index number of the floret and \'\'c\'\' is a constant scaling factor; the florets thus lie on [[Fermat\'s spiral]]. The divergence angle, approximately 137.51°, is the [[golden angle]], dividing the circle in the [[golden ratio]].  Because this ratio is irrational, no floret has a neighbor at exactly the same angle from the center, so the florets pack efficiently.  Because the rational approximations to the golden ratio are of the form F(j):F(j+1), the nearest neighbors of floret number \'\'n\'\' are those at \'\'n\'\'±F(j) for some index \'\'j\'\' which depends on \'\'r\'\', the distance from the center.  It is often said that sunflowers and similar arrangements have 55 spirals in one direction and 89 in the other (or some other pair of adjacent Fibonacci numbers), but this is true only of one range of radii, typically the outermost and thus most conspicuous.<ref>{{cite book\r\n" + 
			"  | last =Prusinkiewicz\r\n" + 
			"  | first =Przemyslaw\r\n" + 
			"  | authorlink =Przemyslaw Prusinkiewicz\r\n" + 
			"  | coauthors =[[Aristid Lindenmayer|Lindenmayer, Aristid]]\r\n" + 
			"  | title =[[The Algorithmic Beauty of Plants]]\r\n" + 
			"  | publisher =Springer-Verlag\r\n" + 
			"  | date= 1990\r\n" + 
			"  | location =\r\n" + 
			"  | pages =101-107\r\n" + 
			"  | url =http://algorithmicbotany.org/papers/#webdocs\r\n" + 
			"  | doi =\r\n" + 
			"  | id = ISBN 978-0387972978 }}</ref>\r\n" + 
			"\r\n" + 
			"== Popular culture ==\r\n" + 
			"{{main|Fibonacci numbers in popular culture}}\r\n" + 
			"Because the Fibonacci sequence is easy for non-mathematicians to understand, there are many examples of the Fibonacci numbers being used in [[popular culture]]. <!--NOTE: YOUR FAVOURITE FIBONACCI REFERENCE SHOULD ONLY BE IN MAIN ARTICLE (Fibonacci numbers in popular culture) AND MAY ALREADY BE THERE!-->\r\n" + 
			"\r\n" + 
			"==Generalizations==\r\n" + 
			"{{main|Generalizations of Fibonacci numbers}}\r\n" + 
			"The Fibonacci sequence has been generalized in many ways. These include:\r\n" + 
			"* Extending to negative index \'\'n\'\', satisfying \'\'F<sub>n</sub>\'\' = \'\'F\'\'<sub>\'\'n\'\'?1</sub> + \'\'F\'\'<sub>\'\'n\'\'?2</sub> and, equivalently, \'\'F<sub>-n</sub>\'\' = (?1)<sup>n+1</sup>\'\'F\'\'<sub>\'\'n\'\'</sub>\r\n" + 
			"* Generalising the index from positive integers to real numbers using a modification of Binet\'s formula. <ref>{{MathWorld|title=Fibonacci Number|urlname=FibonacciNumber|author=Pravin Chandra and [[Eric W. Weisstein]]}}</ref>\r\n" + 
			"* Starting with other integers. [[Lucas number]]s have \'\'L\'\'<sub>1</sub> = 1, \'\'L\'\'<sub>2</sub> = 3, and \'\'L<sub>n</sub>\'\' = \'\'L\'\'<sub>\'\'n\'\'?1</sub> + \'\'L\'\'<sub>\'\'n\'\'?2</sub>. [[Primefree sequence]]s use the Fibonacci recursion with other starting points in order to generate sequences in which all numbers are [[composite number|composite]].\r\n" + 
			"* Letting a number be a linear function (other than the sum) of the 2 preceding numbers. The [[Pell number]]s have \'\'P<sub>n</sub>\'\' = 2\'\'P\'\'<sub>\'\'n\'\' ? 1</sub> + \'\'P\'\'<sub>\'\'n\'\' ? 2</sub>.\r\n" + 
			"* Not adding the immediately preceding numbers. The [[Padovan sequence]] and [[Perrin number]]s have P(n) = P(n ? 2) + P(n ? 3).\r\n" + 
			"* Generating the next number by adding 3 numbers (tribonacci numbers), 4 numbers (tetranacci numbers), or more.\r\n" + 
			"* Adding other objects than integers, for example functions or strings -- one essential example is [[Fibonacci polynomials]].\r\n" + 
			"\r\n" + 
			"==Numbers properties==\r\n" + 
			"===Divisibility by 11===\r\n" + 
			"<math>\\sum_{k=n}^{n+9} F_{k} = 11 F_{n+6}</math>\r\n" + 
			"\r\n" + 
			"===Periodicity of last n digits===\r\n" + 
			"One property of the Fibonacci numbers is that the last n digits have the following periodicity ([[Pisano period]] of powers of 10):\r\n" + 
			"* n = 1 : 60\r\n" + 
			"* n = 2 : 300\r\n" + 
			"* n = 3 : 1500\r\n" + 
			"* n = 4 : 15000\r\n" + 
			"* n = 5 : 150000\r\n" + 
			"Mathematician Dov Jarden proved that for n greater than 2 the periodicity is <math>15\\cdot10^{n-1}</math>.{{Fact|date=November 2007}}\r\n" + 
			"\r\n" + 
			"===Pythagorean triples===\r\n" + 
			"Any four consecutive Fibonacci numbers \'\'F\'\'<sub>\'\'n\'\'</sub>, \'\'F\'\'<sub>\'\'n\'\'+1</sub>, \'\'F\'\'<sub>\'\'n\'\'+2</sub> and \'\'F\'\'<sub>\'\'n\'\'+3</sub> can be used to generate a [[Pythagorean triple]]:\r\n" + 
			":<math> a = F_n F_{n+3} \\, ; \\, b = 2 F_{n+1} F_{n+2} \\, ; \\, c = F_{n+1}^2 + F_{n+2}^2 \\, ; \\,  a^2 + b^2 = c^2 \\,.</math>\r\n" + 
			"Example 1: let the Fibonacci numbers be 1, 2, 3 and 5. Then:\r\n" + 
			":<math> a = 1 \\times 5 = 5</math>\r\n" + 
			":<math> b = 2 \\times 2 \\times 3 = 12</math>\r\n" + 
			":<math> c = 2^2 + 3^2 = 13</math>\r\n" + 
			":<math> 5^2 + 12^2 = 13^2 \\,.</math>\r\n" + 
			"Example 2: let the Fibonacci numbers be 8, 13, 21 and 34. Then:\r\n" + 
			":<math> a = 8 \\times 34 = 272</math>\r\n" + 
			":<math> b = 2 \\times 13 \\times 21 = 546</math>\r\n" + 
			":<math> c = 13^2 + 21^2 = 610</math>\r\n" + 
			":<math> 272^2 + 546^2 = 610^2 \\,.</math>\r\n" + 
			"\r\n" + 
			"==The bee ancestry code==\r\n" + 
			"Fibonacci numbers also appear in the description of the reproduction of a population of idealized bees, according to the following rules:\r\n" + 
			"*If an egg is laid by an unmated female, it hatches a male.\r\n" + 
			"*If, however, an egg was fertilized by a male, it hatches a female.\r\n" + 
			"\r\n" + 
			"Thus, a male bee will always have one parent, and a female bee will have two.\r\n" + 
			"\r\n" + 
			"If one traces the ancestry of any male bee (1 bee), he has 1 female parent (1 bee).  This female had 2 parents, a male and a female (2 bees).  The female had two parents, a male and a female, and the male had one female (3 bees).  Those two females each had two parents, and the male had one (5 bees).  This sequence of numbers of parents is the Fibonacci sequence.<ref>[http://american-university.com/cas/mathstat/newstudents/shared/puzzles/fibbee.html The Fibonacci Numbers and the Ancestry of Bees]</ref>\r\n" + 
			"\r\n" + 
			"This is an idealization that does not describe \'\'actual\'\' bee ancestries. In reality, some ancestors of a particular bee will always be sisters or brothers, thus breaking the lineage of distinct parents.\r\n" + 
			"\r\n" + 
			"==Miscellaneous==\r\n" + 
			"The only squares among the Fibonacci numbers are 0, 1, and 144.<ref>{{cite article | title=Square Fibonacci Numbers Etc |author= J H E Cohn |journal= Fibonacci Quarterly | volume= 2 | year= 1964 | pages=109-113}}</ref>\r\n" + 
			"\r\n" + 
			"==See also==\r\n" + 
			"*[[Logarithmic spiral]]\r\n" + 
			"*[[b:Fibonacci number program]] at [[Wikibooks]]\r\n" + 
			"*[[The Fibonacci Association]]\r\n" + 
			"*[[Fibonacci Quarterly]] &mdash; an academic journal devoted to the study of Fibonacci numbers\r\n" + 
			"*[[Negafibonacci]] numbers\r\n" + 
			"\r\n" + 
			"==References==\r\n" + 
			"{{reflist|2}}\r\n" + 
			"\r\n" + 
			"==External links==\r\n" + 
			"* Peter Marcer, \'\'[http://cat.inist.fr/?aModele=afficheN&cpsidt=5221044 describing the discovery by jean-claude Perez of Fibonacci numbers structuring proportions of TCAG nucleotides within DNA]\'\', (1992).\r\n" + 
			"* Ron Knott, \'\'[http://www.mcs.surrey.ac.uk/Personal/R.Knott/Fibonacci/phi.html The Golden Section: Phi]\'\', (2005).\r\n" + 
			"* Ron Knott, \'\'[http://www.mcs.surrey.ac.uk/Personal/R.Knott/Fibonacci/fibrep.html Representations of Integers using Fibonacci numbers]\'\', (2004).\r\n" + 
			"* Juanita Lofthouse \'\'[http://arxiv.org/abs/physics/0411169 Fibonacci numbers and Red Blood Cell Dynamics]\'\', .\r\n" + 
			"* Bob Johnson, \'\'[http://www.dur.ac.uk/bob.johnson/fibonacci/ Fibonacci resources]\'\', (2004)\r\n" + 
			"* Donald E. Simanek, \'\'[http://www.lhup.edu/~dsimanek/pseudo/fibonacc.htm Fibonacci Flim-Flam]\'\', (undated, 2005 or earlier).\r\n" + 
			"* Rachel Hall, \'\'[http://www.sju.edu/~rhall/Multi/rhythm2.pdf Hemachandra\'s application to Sanskrit poetry]\'\', (undated; 2005 or earlier).\r\n" + 
			"* Alex Vinokur, \'\'[http://semillon.wpi.edu/~aofa/AofA/msg00012.html Computing Fibonacci numbers on a Turing Machine]\'\', (2003).\r\n" + 
			"* (no author given), \'\'[http://www.goldenmeangauge.co.uk/fibonacci.htm Fibonacci Numbers Information]\'\', (undated, 2005 or earlier).\r\n" + 
			"* [http://www.mcs.surrey.ac.uk/Personal/R.Knott/Fibonacci/fib.html Fibonacci Numbers and the Golden Section] ? Ron Knott\'s Surrey University multimedia web site on the Fibonacci numbers, the Golden section and the Golden string. \r\n" + 
			"* The [http://www.mscs.dal.ca/Fibonacci/ Fibonacci Association] incorporated in [[1963]], focuses on Fibonacci numbers and related mathematics, emphasizing new results, research proposals, challenging problems, and new proofs of old ideas.\r\n" + 
			"* Dawson Merrill\'s [http://www.goldenratio.org/info/ Fib-Phi] link page.\r\n" + 
			"* [http://primes.utm.edu/glossary/page.php?sort=FibonacciPrime Fibonacci primes]\r\n" + 
			"* [http://www.mathpages.com/home/kmath078.htm Periods of Fibonacci Sequences Mod m] at MathPages\r\n" + 
			"* [http://www.upl.cs.wisc.edu/~bethenco/fibo/ The One Millionth Fibonacci Number]\r\n" + 
			"* [http://www.bigzaphod.org/fibonacci/ The Ten Millionth Fibonacci Number]\r\n" + 
			"* An [http://www.calcresult.com/maths/Sequences/expanded_fibonacci.html Expanded Fibonacci Series Generator]\r\n" + 
			"* Manolis Lourakis, [http://www.ics.forth.gr/~lourakis/fibsrch/ Fibonaccian search in C]\r\n" + 
			"* [http://www.physorg.com/news97227410.html Scientists find clues to the formation of Fibonacci spirals in nature]\r\n" + 
			"*[http://mathdl.maa.org/convergence/1/?pa=content&sa=viewDocument&nodeId=630&bodyId=1002 Fibonacci Numbers] at [http://mathdl.maa.org/convergence/1/ Convergence]\r\n" + 
			"* [http://www.tools4noobs.com/online_tools/fibonacci/ Online Fibonacci calculator]\r\n" + 
			"\r\n" + 
			"[[Category:Fibonacci numbers|*]]\r\n" + 
			"[[Category:Articles containing proofs]]\r\n" + 
			"\r\n" + 
			"[[ar:??????? ?????????]]\r\n" + 
			"[[bn:?????????? ????????]]\r\n" + 
			"[[bs:Fibonaccijev broj]]\r\n" + 
			"[[bg:????? ?? ????????]]\r\n" + 
			"[[ca:Successió de Fibonacci]]\r\n" + 
			"[[cs:Fibonacciho posloupnost]]\r\n" + 
			"[[da:Fibonacci-tal]]\r\n" + 
			"[[de:Fibonacci-Folge]]\r\n" + 
			"[[el:????????? ??????????]]\r\n" + 
			"[[es:Sucesión de Fibonacci]]\r\n" + 
			"[[eo:Fibona?i-nombroj]]\r\n" + 
			"[[eu:Fibonacciren zenbakiak]]\r\n" + 
			"[[fa:????? ????????]]\r\n" + 
			"[[fr:Suite de Fibonacci]]\r\n" + 
			"[[ko:???? ?]]\r\n" + 
			"[[hi:????????? ??????]]\r\n" + 
			"[[hr:Fibonaccijev broj]]\r\n" + 
			"[[id:Bilangan Fibonacci]]\r\n" + 
			"[[is:Fibonacci runan]]\r\n" + 
			"[[it:Successione di Fibonacci]]\r\n" + 
			"[[he:???? ???????\'?]]\r\n" + 
			"[[lv:Fibona?i skait?i]]\r\n" + 
			"[[lt:Fibona?io skai?ius]]\r\n" + 
			"[[hu:Fibonacci-számok]]\r\n" + 
			"[[nl:Rij van Fibonacci]]\r\n" + 
			"[[ja:???????]]\r\n" + 
			"[[no:Fibonacci-tall]]\r\n" + 
			"[[pl:Ci?g Fibonacciego]]\r\n" + 
			"[[pt:Número de Fibonacci]]\r\n" + 
			"[[ru:????? ?????????]]\r\n" + 
			"[[scn:Succissioni di Fibonacci]]\r\n" + 
			"[[sk:Fibonacciho postupnos?]]\r\n" + 
			"[[sl:Fibonaccijevo ?tevilo]]\r\n" + 
			"[[sr:??????????? ???]]\r\n" + 
			"[[fi:Fibonaccin lukujono]]\r\n" + 
			"[[sv:Fibonaccital]]\r\n" + 
			"[[ta:?????????? ??????]]\r\n" + 
			"[[th:????????????]]\r\n" + 
			"[[vi:Dãy Fibonacci]]\r\n" + 
			"[[tr:Fibonacci serisi]]\r\n" + 
			"[[uk:????????????? ?????????]]\r\n" + 
			"[[vls:Reke van Fibonacci]]\r\n" + 
			"[[zh:??????]]\r\n" + 
			"";

	public BigRenderFibonacciNumberTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(BigRenderFibonacciNumberTest.class);
	}

	public void test999() {
		for (int i = 0; i < 100; i++) {
			wikiModel.render(TEST1);
		}
	}

}
