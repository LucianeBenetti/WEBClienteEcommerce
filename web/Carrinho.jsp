<%-- 
    Document   : Carrinho
    Created on : 30/05/2019, 15:11:37
    Author     : 80119050
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="controle.VO.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <title>LuMar</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script>

    </script>

    <style>
        /* Remove the navbar's default rounded borders and increase the bottom margin */ 
        .navbar {
            margin-bottom: 10px;
            border-radius: 0;
            padding:0px;
        }

        /* Remove the jumbotron's default bottom margin */ 
        .jumbotron {
            margin-bottom: 0;
            padding: 0px;
        }

        /* Add a gray background color and some padding to the footer */
        footer {
            background-color: #f2f2f2;
            color: red;
            padding: 5px;
            margin-top: 35%;
        }
    </style>
</head>
<body>


    <div class="container">
        <h2>Selecione os Itens a serem adicionados ao Carrinho!</h2>

        <%
            Object obj = request.getAttribute("listadeitens");
            ArrayList<Item> listaDeItens = (ArrayList<Item>) obj;
            %><table class="table table-hover">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Descrição</th>
                    <th>Preço</th>
                    <th>Qntde</th>
                    <th>Alterar</th>
                    <th>Ecluir</th>
                </tr>
            </thead><%
            if (listaDeItens != null) {
                
                for (int i = 0; i < listaDeItens.size(); i++) {

        %>                          
        
            <tbody>
                <tr>
                    <td><% out.print(listaDeItens.get(i).getNome()); %></td>
                    <td><% out.print(listaDeItens.get(i).getDescricao()); %></td>
                    <td><% out.print(listaDeItens.get(i).getValor());%></td>
                    <td> <input type="number" style="width: 50px" min="0" value="0" name="quantidade_<%= i%>"></td>
                    <td><i class="fas fa-edit"></i></td>
                    <td><i class="fas fa-trash-alt"></i></td>
                </tr>
            </tbody>
        </table>
        <%}
        }%>
    </div>    
    <footer class="container-fluid text-center">
        &copy; Desenvolvido por Luciane Benetti e Marco Sena.  
    </footer>      
</body>
</html>
