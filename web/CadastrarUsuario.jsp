<%-- 
    Document   : CadastrarUsuario
    Created on : 21/05/2019, 09:01:46
    Author     : 80119050
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>Cadastrar Usuário</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        
        <div class="container">
            <h2>Loja Digital LuMar!</h2>
            <p> Cadastrar Usuario!</P>
            <form class="was-validated" action="http://localhost:8080/WEBServicosEcommerce/crudecommerce" method="post">
                <div class="form-group">
                    <label for="nome">Login:</label>
                    <input type="text" class="form-control" id="login" placeholder="Digite um nome para o seu login" name="login" required>
                    <div class="valid-feedback">Campo preenchido. Ok!</div>
                    <div class="invalid-feedback">Esse campo é obrigatório.</div>
                </div>
                <div class="form-group">
                    <label for="pwd">Senha:</label>
                    <input type="password" class="form-control" id="sena" placeholder="Digite uma senha" name="senha" required>
                    <div class="valid-feedback">Campo preenchido. Ok!</div>
                    <div class="invalid-feedback">Esse campo é obrigatório.</div>
                    <small id="senha" class="form-text text-muted">Nunca compartilhe a sua senha!</small>                
                </div>

                <input type="hidden" id="cadastrar" name="cadastrar" value="cadastrar">
                <button type="submit" class="btn btn-primary">Cadastrar Usuário</button>

                <div class="form-group form-check" style =" float: top">
                    <label class="form-check-label">
                        <input class="form-check-input" type="checkbox" name="remember"> Lembre-me!
                    </label>
                </div> <br>           

            </form>

        </div>
    

<div class="jumbotron text-center" style="margin-bottom: 0%; height: 0.5%; padding: 0.5%;">
  <p>&copy; Desenvolvido por Luciane Benetti e Marco Sena.</p>
</div>
        
    </body>
</html>
