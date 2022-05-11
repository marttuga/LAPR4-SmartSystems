# US1002
=======================================

# 1. Requirements

**US1901** As Project Manager, I want that the "OrdersServer" component supports properly, at request, the needs of the "CustomerApp" application.

The interpretation made of this requirement was that the project manager wishes that the server application, called 
“Orders Server”, responsible to fulfill the all the data requests necessary to the “Customer Application” works properly. 
The set of available/possible requests must be minimized and, each one, should have a very specific purpose (e.g.: add a 
product to the shopping cart, check the status of his/her open orders).

## Acceptance criteria
* It must be used the provided application protocol (SPOMS2022).
* It is suggested the adoptiong of concurrent mechanisms (e.g. threads) and state sharing between these mechanisms.
* In this sprint, for demonstration purposes, it is acceptable to mock processing some of the incoming requests.


# 2. Analysis
* To complete this user story, the us1001 (responsable for specifying a new product for sale),the  us1002 (responsable for
viewing the catalog), the us1004 (responsable for registering new orders) and us1005 (responsible for 
defining a new category of products) are going to be necessary.
* This user story focuses on creating solutions to common requests that may be asked from the customers point of view, for example
checking the status of his/her open orders... 

# 3. Design
* Utilizar a estrutura base standard da aplicação baseada em camadas 

>   Classes do domínio: Category, Product, Barcode, Brand, ShortDescription, ProductPriceDetail
>
>   Controlador: ViewCatalogController
>
>   Repository:  ProductRepository

## 3.1. Realização da Funcionalidade
![SSD](US1901_SSD.svg)

![SD](US1901_SD.svg)

## 3.2. Diagrama de Classes
![CD](US1901_CD.svg)


*Nesta secção deve apresentar e descrever as principais classes envolvidas na realização da funcionalidade.*

## 3.3. Padrões Aplicados

*Nesta secção deve apresentar e explicar quais e como foram os padrões de design aplicados e as melhores práticas.*

## 3.4. Testes
*Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos.*

**Teste 1:** Verificar se duas brands sao iguais

	  @Test
    void testEquals() throws IllegalAccessException {
        Brand b= new Brand("oi");
        Brand bo= new Brand("oi");
        Assertions.assertEquals(bo.toString(), b.toString());
    }

# 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*

# 5. Integração/Demonstração

    public List<Product> getProductByBrand(Brand brand) {
        return productRepository.findByBrand(brand);
    }

    public List<Product> getProductByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductByDescription(ShortDescription shortDescription) {
        return productRepository.findByDescription(shortDescription);
    }
# 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*



