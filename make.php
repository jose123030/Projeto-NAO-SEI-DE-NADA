<?php
define("DS", DIRECTORY_SEPARATOR);
define("HOME", getenv("HOME"));


class Make {


	function __construct($arg) {
		if (!isset($arg)) {
			echo "Comando inválido";
			var_dump($_SERVER);
			exit(0);
		}

		switch($arg) {
			case 'config':
				$this->config();
				break;
			case 'compile':
				$this->compile();
				break;
		    case 'run-tests':
		        $this->runTests();
		        break;
		}
	}

	function config() {
		$dest = HOME."/.ravac/libs/";
		if (is_dir("{$dest}libs") || is_dir("{$dest}test-libs")) {
			system("mv {$dest}* {$dest}");
			system("mv {$dest}*/* {$dest}");
			system("cd {$dest} && rm -rf libs test-libs test-libs*");
		}

		$libs = [
			"assertj-core-3.8.0.jar" => "http://central.maven.org/maven2/org/assertj/assertj-core/3.8.0/assertj-core-3.8.0.jar",
			"hamcrest-core-1.3.jar" => "http://central.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar",
			"junit-4.12.jar" => "http://central.maven.org/maven2/junit/junit/4.12/junit-4.12.jar",
			"selenium-server-standalone-3.13.0.jar" => "https://selenium-release.storage.googleapis.com/3.13/selenium-server-standalone-3.13.0.jar"
		];
		foreach($libs as $lib => $url) {
			if (!is_file("{$dest}{$lib}")) {
	                        echo "Downloading {$lib}...\n";
				file_put_contents("{$dest}{$lib}", fopen($url, 'r'));
			}
		}
	}

	function compile() {
		if (!$this->compileClasses()) {
			return 1;
		}
		if (!$this->compileTest()) {
			return 1;
		}
	}

	function compileTest() {
		$path = 'test';
		$file = 'Teste.java';
		if (func_num_args() == 2) {
			$path = func_get_arg(0);
			$file = func_get_arg(1);
		}
		echo "Compilando testes em '{$path}'...\n";
		system("cd {$path} && rm *.class");
		$val = 0;
		system(sprintf("javac -cp \".:%s/.ravac/libs/*\" {$path}/{$file}", HOME), $val);
		if ($val != 0) {
			echo "Erro na compilação dos testes em {$path}\n";
			return false;
		}
		echo "Ok\n";
		return true;
	}

	function compileClasses() {
		echo "Compilando classes da aplicação...\n";
		system("cd app && rm *.class");
		$val = 0;
		system("cd app && javac *.java", $val);
		if ($val != 0) {
			echo "Erro na compilação das classes da aplicação\n";
			return false;
		}
		echo "Ok\n";
		return true;
	}

	function runTests() {
		$path = 'test';
		$class = 'Teste';
		if (func_num_args() == 2) {
			$path = func_get_arg(0);
			$class = func_get_arg(1);
		}
		if (is_file("{$path}/{$class}.class")) {
			system(sprintf("clear && java -cp \".:%s/.ravac/libs/*\" {$path}.{$class}", HOME));
			return;
		}
		echo "Testes não compilados\n";
	}
}

if (is_file('_make.php')) {
	include('_make.php');
	exit();
}

new Make($argv[1]);


?>
