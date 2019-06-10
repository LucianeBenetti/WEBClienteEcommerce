
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>LuMar</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

        <style>
            /* Remove the navbar's default rounded borders and increase the bottom margin */ 
            .navbar {
                background-color: #191970;
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
            }
        </style>
    </head>
    <body>
        <div class="container text-center">

            <h4>Digital LuMar - Confira abaixo os produtos oferecidos pela melhor loja da Internet, 
                ou pesquise por um produto específico</h4>
            <form action="pesquisaritem" method="get">
                Pesquise aqui:
                <input type="text" size="60" name="descricaoproduto" placeholder="Digite a descrição de um produto!">
                <input class="btn btn-danger" type="submit"  value="Pesquisar!"><br><br>

            </form>

        </div>

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
                                <a class="dropdown-item" href="listartodospedidos">Listar/Cancelar Pedidos</a><br>
                                <a class="dropdown-item" href="atualizarcartao">Alterar Forma de pagamento</a><br>
                            </div>
                        </li>

                        <li><input class="btn" style="margin: 14px 0px 10px 10px; padding: 0px;"  size="10" type="text" value="<%out.println("Olá " + request.getAttribute("login") + "!");%>"></li>
                        <li><a href="carrinho"><span class="glyphicon glyphicon-shopping-cart"></span>Carrinho</a></li>
                        <li>
                            <form action="sairdosistema" method="post">
                                
                                <input class="btn" type="submit" value="Sair" style="padding: 2px; margin-top: 12px;">  
                            </form> 
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container">    
            <div class="row">
                <div class="col-sm-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">Bola de vôlei Mikasena</div>
                        <div class="panel-body"><img src="bolapraia.jpg" class="img-responsive" style="width:58%" alt="Bola de vôlei de praia"></div>
                        <div class="panel-footer">A melhor bola de vôlei de praia! Só R$ 3,00.</div>
                    </div>
                </div>
                <div class="col-sm-4"> 
                    <div class="panel panel-danger">
                        <div class="panel-heading">Violão Takasena.</div>
                        <div class="panel-body"><img src="takamine.jpg" class="img-responsive" style="width:100%" alt="Violão Takamine"></div>
                        <div class="panel-footer">Violão Takasena corda de Lynon! Só R$ 15,00.</div>
                    </div>
                </div>
                <div class="col-sm-4"> 
                    <div class="panel panel-success">
                        <div class="panel-heading">Celular Senasung</div>
                        <div class="panel-body"><img src="celular.jpg" class="img-responsive" style="width:67%" alt="Image"></div>
                        <div class="panel-footer">Celuar M10 top de linha! Só R$ 2,00.</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">    
            <div class="row">
                <div class="col-sm-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">Carro</div>
                        <div class="panel-body"><img src="carro.jpg" class="img-responsive" style="width:80%" alt="Image"></div>
                        <div class="panel-footer">Carro modelo MS500 500 CV! Só R$ 35,00.</div>
                    </div>
                </div>
                <div class="col-sm-4"> 
                    <div class="panel panel-primary">
                        <div class="panel-heading">Micro Msena</div>
                        <div class="panel-body"><img src="micro.jpg" class="img-responsive" style="width:88%" alt="Image"></div>
                        <div class="panel-footer">Micro Msena 555 top de linha! R$ 5,00.</div>
                    </div>
                </div>
                <div class="col-sm-4"> 
                    <div class="panel panel-primary">
                        <div class="panel-heading">Tablete Lbenetti</div>
                        <div class="panel-body"><img src="tablet.jpg" class="img-responsive" style="width:54%" alt="Image"></div>
                        <div class="panel-footer">Tablete Lb 2106 com 1TB de SRAM! Só R$ 4,00.</div>
                    </div>
                </div>
            </div>
        </div><br><br>

        <footer class="container-fluid text-center">
            &copy; Desenvolvido por Luciane Benetti e Marco Sena.  
        </footer>

    </body>
</html>
