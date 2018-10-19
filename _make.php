<?php
class Make06 extends Make {
	function compileTest() {
        parent::compileTest('test', 'TesteExecutionListener.java');
        parent::compileTest('structure', 'Teste.java');
		parent::compileTest('functions', 'Teste.java');
	}

	function runTests() {
        parent::runTests('structure', 'Teste');
        parent::runTests('functions', 'Teste');
	}
}
new Make06($argv[1]);


?>
