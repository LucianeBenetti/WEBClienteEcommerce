<%-- 
    Document   : MostrarPedido
    Created on : 31/05/2019, 13:43:32
    Author     : 80130917
--%>

<%@page import="controle.VO.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controle.VO.PedidoCompra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mostrar Pedido</title>
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
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" style="padding:4px; float: bottom"><i class="fas fa-globe" style="font-size:40px;color:red;"></i></a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="voltarhomeautenticado">Home</a></li>
                        <li><a href="carrinho">Comprar</a></li>
                        <li><a href="maisvendidos">Mais Vendidos</a></li>
                        <li><a href="#">Contato</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span>
                                Minha Conta
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <a class="dropdown-item" href=".jsp">Listar/Cancelar Pedidos</a><br>
                                <a class="dropdown-item" href="atualizarcartao">Alterar Forma de pagamento</a><br>
                            </div>
                        </li>

                        <li><input class="btn" style="margin: 14px 0px 10px 10px; padding: 0px;"  size="10" type="text" value="<%out.println("Olá " + request.getAttribute("nomeusuario") + "!");%>"></li>
                        <li><a href="carrinho"><span class="glyphicon glyphicon-shopping-cart"></span>Carrinho</a></li>
                        <li>
                            <form action="fecharpedido" method="post">
                                <input type="hidden" id="sairdosistema" name="sairdosistema" value="sairdosistema">
                                <input class="btn" type="submit" name="sairdosistema" value="Sair">  
                            </form> 
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container">

            <h2>Detalhes do Pedido</h2>
            <%
                Object pedidoUsuario = request.getAttribute("pedidocompra");
                Object valorTotal = request.getAttribute("valortotal");
                Object quantidades = request.getAttribute("quantidades");
                Object nomeUsuario = request.getAttribute("nomeusuario");
                Object numeroCartao = request.getAttribute("numerocartao");

                if (pedidoUsuario != null) {
                    ArrayList<Item> itemPedido = (ArrayList<Item>) pedidoUsuario;
                    ArrayList<Integer> qtidades = (ArrayList<Integer>) quantidades;

            %>   

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Descrição</th>
                        <th>Preço do Item</th>
                        <th>Qntde</th>
                    </tr>
                </thead>
                <%      for (int i = 0; i < itemPedido.size(); i++) {
                        Item pedidoItem = itemPedido.get(i);
                        if (qtidades.get(i) > 0) {
                %>
                <tr>
                    <td><% out.print(pedidoItem.getNome()); %></td>
                    <td><% out.print(pedidoItem.getDescricao()); %></td>
                    <td><% out.print(pedidoItem.getValor());%></td>
                    <td><%=qtidades.get(i)%></td>
                </tr>
                <%}
                    }
                %>

                <br />  

                <tr>
                    <td class="total" colspan="2">Total</td>
                    <td>R$ <%= valorTotal%></td>
                </tr>

            </table><br>

            <form action="fecharpedido" method="get"><br>

                <h4>Dados para cobrança!</b></h4> <br><br>

                <div class="form-group">
                    <label for="nome">Nome do Usuário:</label>
                    <input type="text" disabled value="<% out.print(nomeUsuario); %>" name="login" class="form-control" id="login" required>
                </div>

                <div class="form-group">
                    <label for="nome">Número do Cartão:</label>
                    <input type="text" disabled value="<% out.print(numeroCartao); %>" name="numerocartao" class="form-control" id="numerocartao" required>
                </div>

            </form>

            <form action="fecharpedido" method="get"><br>      
                <input type="hidden" id="fecharpedido" name="fecharpedido" value="fecharpedido">
                <button type="submit" class="btn btn-primary">Fechar Pedido</button>

            </form>
        </div>
        <%}%>

    </body>
</html>

