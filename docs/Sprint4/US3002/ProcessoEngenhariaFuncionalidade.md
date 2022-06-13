# US3002
=======================================

# 1. Requirements

**US3002** As Sales Manager, I want to get a statistical report regarding a previously set up questionnaire.

* Within the scope of this user story, the Sales Manager wants to have a statistical overview report of a certain questionnaire.
* In this report are stated the size of the universe being considered, including the ammount of users that responded as well as the amount of answers.
* For each question, the Sales Clerk can see the percentage that each choice was selected.


## Acceptance criteria
* The report should be generated considering the given questionnaire and the set of answers obtained until the current moment.

# 2. Analysis

* This US depends on the US 3001 that creates the questionnaires and the US 3501 that registers the answers to them.
* For this US, the team must specify a parser that validates the various types of questionnaires and produces a statistical report on each of the previously configured questionnaires.
*

# 3. Design


>   Domain Classes: Costumer, CustomerFirstName, CustomerLastName, CustomerEmailAddress, CustomerPostalAddress, CustomerPhoneNumber, CustomerBirthday, CustomerGender, CustomerVatIdentifier
>
>   Controller: RegisterCustomerController
>
>   Repository: CostumerRepository

## 3.1. Realização da Funcionalidade

![SSD](US1003_SSD.svg)

![SD](US1003_SD.svg)

## 3.2. Diagrama de Classes

![CD](US1003_CD.svg)


## 3.3. Padrões Aplicados

*Nesta secção deve apresentar e explicar quais e como foram os padrões de design aplicados e as melhores práticas.*

## 3.4. Testes 
*Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos.*

**Teste 1:** Verificar que não é possível criar uma instância da classe Exemplo com valores nulos.

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Exemplo instance = new Exemplo(null, null);
	}

# 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*

# 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*

# 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*



