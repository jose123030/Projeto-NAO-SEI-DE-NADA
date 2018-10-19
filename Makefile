all: help

config:
	@php make.php config

compile: config
	php make.php compile

run-tests:
	@php make.php run-tests

happy: compile run-tests

zip:
	@while [ -z "$$STUDENT" ]; do \
		read -r -p "Digite seu nome, removendo acentos e trocando os espaços por traços (-): " STUDENT; \
	done ; \
	zip -r $$STUDENT.zip app



@task:
	@rm -rf $(name)
	@mkdir -p $(name)/app
	@mkdir -p $(name)/test/libs
	@cp test/*.java $(name)/test/
	@cp Makefile $(name)/
	@zip -r $(name).zip $(name)/
	@rm -rf $(name)

@prepare:
	@mkdir -p app
	@mkdir -p test
	@ln -s ../make.php .
	@cd test && ln -s ../../Teste.java .



help:
	@clear
	@echo "+----------------------------------------------------------------------------+"
	@echo "| Ajuda                                                                      |"
	@echo "+----------------------------------------------------------------------------+"
	@echo "| compile:  compila as classes de teste em Java dentro da pasta 'test'       |"
	@echo "| run:      roda os testes e mostra a(s) nota(s) ao final                    |"
	@echo "| server:   inicia o servidor, setando o root para o diretório 'app'         |"
	@echo "| happy:    compila e executa os testes, sem mais preocupações               |"
	@echo "| zip:      compacta o diretório 'app' dentro de um arquivo com o seu nome   |"
	@echo "+----------------------------------------------------------------------------+"
