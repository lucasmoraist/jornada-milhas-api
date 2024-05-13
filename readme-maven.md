# Maven 
## Configurando perfis de trabalho
- Os perfis são encontrados pelo maven através do nome que damos para os arquivos, exemplo
  - `application.properties`
  - `application-test.properties`
  - `application-prod.properties`
- `activation` && `activeByDefault` - utilizamos para definir qual será o perfil padrão durante o build
```
<profiles>
    <profile>
	    <id>dev</id>
	    <properties>
	        <activatedProperties>dev</activatedProperties>
	    </properties>
    </profile>
    <profile>
        <id>prod</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <activatedProperties>prod</activatedProperties>
        </properties>
    </profile>
    <profile>
        <id>test</id>
        <properties>
            <activatedProperties>test</activatedProperties>
        </properties>
    </profile>
</profiles>
```

## Build pelo terminal

### Está etapa é responsável por limpar o diretório do build
```
mvn clean
```

### Está etapa é responsável pelo empacotamento do projeto
- Podemos rodar o `mvn clean` junto com ela
```
mvn package || mvn clean package
```

### Está etapa é responsável por gerar a documentação do projeto
**Ela inclui:**
- Relatório de testes
- Relatório de métricas de código

```
mvn site
```

## Plugins
- <a href="https://maven.apache.org/plugins/index.html">Clique aqui</a> para ver mais plugins

### pmd
- Irá gerar um relatório na pasta sites
```
./mvnw pmd:pmd
```
