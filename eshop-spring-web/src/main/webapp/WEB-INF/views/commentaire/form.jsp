<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- ETAPE 5 : Génération de la View avec les données du Model --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edition d'un commentaire</title>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap-icons.css"/>">
</head>
<body>

	<div class="container">
		<div class="card mt-3">
			<c:url value="/commentaire/save" var="saveUrl"/>
			<form action="${saveUrl}" method="post">
				<input type="hidden" name="id" value="${commentaire.id}" />
				<div class="card-header bg-primary text-white display-6">Edition d'un commentaire</div>
				<div class="card-body">
					<div class="form-group">
						<label for="date">Date:</label> <input type="datetime-local"
							class="form-control" id="date" name="date"
							value="<fmt:formatDate value="${commentaire.date}" pattern="yyyy-MM-dd'T'HH:mm"/>" />
					</div>
					<div class="form-group">
						<label for="note">Note:</label> <input type="number"
							class="form-control" id="note" name="note"
							value="${commentaire.note}" />
					</div>
					<div class="form-group">
						<label for="commentaire">Commentaire:</label>
						<textarea class="form-control" id="commentaire" name="commentaire" rows="5">${commentaire.commentaire}</textarea>
					</div>
					<div class="form-group">
						<label for="idProduit">Produit:</label>
						<select class="form-control" id="idProduit" name="idProduit">
							<option value="">Sélectionner un produit</option>
							<c:forEach items="${produits}" var="prod">
								<option value="${prod.id}" ${commentaire.produit.id == prod.id ? 'selected' : ''}>${prod.libelle}</option>
							</c:forEach>
						</select> 
					</div>

				</div>
				<div class="card-footer">
					<c:url value="/commentaire/cancel" var="cancelUrl"/>
					<div class="btn-group btn-group-lg float-right">
						<button type="submit" class="btn btn-success"><i class="bi bi-check-square-fill"></i></button>
						<a href="${cancelUrl}" class="btn btn-warning"><i class="bi bi-backspace-fill"></i></a>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script src="<c:url value="/js/bootstrap.bundle.js"/>"></script>
</body>
</html>