# US1002
=======================================

# 1. Requirements

**US2003** As Warehouse Employee, I want to access the list of orders that need to be prepared by an AGV and be able to ask/force any of those orders to be immediately prepared by an AGV available.

The interpretation made of this requirement was that the sales clerck wishes to view the products of the catalog, and/or search 
for a specific one and its details.
They start by filtering all the products using a category, description and/or brand, then the data should appear ordered,
by price or alphabetically. Finally, the product must be chosen so that the details can be viewed.

## Acceptance criteria


# 2. Analysis
* To complete this user story, the us1004 (responsable for registering new orders) and us2002 (responsible for configuring the AGVs) are going to be necessary.
* The warehouse employee should be able to access the list of previous orders and check their status to see which ones need to be 
prepared by an AGV (in the status to be prepared)
* After this, they must check the AGVs and seek the available ones to ask/force any of those orders to be immediately prepared by one of them (Status becomes: being_prepared)


# 3. Design
* Utilizar a estrutura base standard da aplicação baseada em camadas 

>   Classes do domínio: Category, Product, Barcode, Brand, ShortDescription, ProductPriceDetail
>
>   Controlador: ViewCatalogController
>
>   Repository:  ProductRepository

## 3.1. Realização da Funcionalidade
![SSD](US2003_SSD.svg)

![SD](US2003_SD.svg)

## 3.2. Diagrama de Classes
![CD](US2003_CD.svg)


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



