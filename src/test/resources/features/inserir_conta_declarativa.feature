#language: pt
Funcionalidade: Cadastro de Contas

    Como um usuário
    Gostaria de cadastrar contas
    Para que eu possa distribuir meu dinheiro de uma forma mais organizada

#Background
Contexto:
	Dado que desejo adicionar uma conta
	
Esquema do Cenário: Deve validar o cadastro de contas
	Quando adiciono a conta "<conta>"
	Então recebo a mensagem "<mensagem>"

Exemplos:
	| conta              | mensagem                           |
	| Conta de Teste     | Conta adicionada com sucesso!      |
	|                    | Informe o nome da conta            |
	| Conta de Teste     | Já existe uma conta com esse nome! |

#Cenário: Deve inserir uma conta com sucesso
#	E informo a conta "Conta de Teste"
#	E seleciono Salvar
#	Então a conta é inserida com sucesso
#
#Cenário: Não deve inserir uma conta sem nome
#	E seleciono Salvar
#	Então sou notificado de que o nome da conta é obrigatório
#
#Cenário: Não deve inserir uma conta com nome já existe
#	E informo a conta "Conta de Teste"
#	E seleciono Salvar
#	Quando seleciono Contas
#	E seleciono Adicionar
#	E informo a conta "Conta de Teste"
#	E seleciono Salvar
#	Então sou notificado de que já existe uma conta com esse nome
	