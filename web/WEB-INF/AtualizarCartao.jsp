<%@page import="controle.VO.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Atualizar Cartão</title>
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
        <%

            Object nomeUsuario = request.getAttribute("nomeusuario");
            Object numeroCartao = request.getAttribute("numerocartao");
            if (nomeUsuario != null) {
        %> 
        <div class="container">

            <form action="fecharpedido" method="get"><br>

                <h4>Confira o <b>Número do seu Cartão</b>. Se não estiver correto, digite o novo número e clique em Atualizar Cartão!</h4> <br><br>
                <div class="form-group">
                    <label for="nome">Nome do Usuário:</label>
                    <input type="text" disabled value="<% out.print(nomeUsuario); %>" name="login" class="form-control" id="login" required>
                </div>

                <div class="form-group">
                    <label for="nome">Número do Cartão:</label>
                    <input type="text" value="<% out.print(numeroCartao);%>" name="numerocartao" class="form-control" id="numerocartao" required>
                </div>
                <input type="hidden" id="atualizarcartao" name="atualizarcartao" value="atualizarcartao">
                <input type="submit" class="btn btn-danger" value="Atualizar Cartão">
            </form>

            <%}

                Object cartaoAtualizado = request.getAttribute("cartaoatualizado");
                if (cartaoAtualizado != null) {
            %>

            <div class="container">
                <h2>O número do seu cartão foi atualizado com sucesso!!</h2>
            </div>
            <%}%>

        </div>
    </body>
</html>
