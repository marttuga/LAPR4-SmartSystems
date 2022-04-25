# US2001
=======================================

# 1. Requirements

**US2001** As Warehouse Employee, I want to set up the warehouse plan by uploading a JSON file.

- Create a warehouse plan by uploading a JSON file that has the warehouse layout/organization.
  
I interpret that:
* Each row states how many shelves it has. As so, if a row states it has 4 shelves, it means that the row shelves are identified as shelf 1, 2, 3 and 4 from the bottom to the top.
* As shelves in rows are identified from bottom to top.

## Acceptance criteria
* A default file can be loaded automatically.
* If a JSON file is found may be updated the information about the warehouse plan.
* Warehouse plant persistence must be done in the database because the JSON file might not be available on all workstations used by warehouse employees.
* US 2001 is just to set up the warehouse plant by uploading a file. As so, it is not necessary to graphically display the warehouse plant.


# 2. Analysis

- Creation of a JSON file that contains all the information necessary for the location of any product in a warehouse, that is, having all the storage areas defined (with coordinates).
- In addition, facilitate the user's request to open and update the information of the warehouse plan and finally save it.


# 3. Design

* Use a layered application framework base pattern.

* Domain Classes: UploadFile
* Controller: UploadFileController
* Repository: UploadFileRepository


## 3.1. Realização da Funcionalidade

[US2001_SD](/docs/Sprint2/US2001/US2001_SD.puml)

[US2001_SSD](/docs/Sprint2/US2001/US2001_SSD.puml)

## 3.2. Diagrama de Classes

[US2001_CD](/docs/Sprint2/US2001/US2001_CD.puml)

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



