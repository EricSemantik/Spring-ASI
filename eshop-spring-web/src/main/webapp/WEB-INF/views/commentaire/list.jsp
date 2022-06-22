<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- ETAPE 5 : Génération de la View avec les données du Model --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des commentaires</title>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap-icons.css"/>">
</head>
<body>
	
	<div class="container">
		<div class="card mt-3">
			<div class="card-header bg-primary text-white display-6">Liste des commentaires</div>
			<div class="card-body">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Identifiant</th>
							<th>Date</th>
							<th>Note</th>
							<th>Commentaires</th>
							<th>Produit</th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${commentaires}" var="commentaire">
							<c:url value="/commentaire/edit" var="editUrl">
								<c:param name="id" value="${commentaire.id}"/>
							</c:url>
							<c:url value="/commentaire/delete/${commentaire.id}" var="deleteUrl"/>
							<tr>
								<td>${commentaire.id}</td>
								<td>${df.format(commentaire.date)}</td>
								<td>${commentaire.note}</td>
								<td>${commentaire.commentaire}</td>
								<td></td>
								<td><div class="btn-group btn-group-sm">
								    <a href="${editUrl}" class="btn btn-primary"><i class="bi bi-pencil-square"></i></a>
								    <a href="${deleteUrl}" class="btn btn-danger"><i class="bi bi-trash"></i></a>
								 </div></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
			<c:url value="/commentaire/add" var="addUrl"/>
			
			<div class="card-footer">
				<a href="${addUrl}" class="btn btn-success btn-lg"><i class="bi bi-plus-square"></i></a>
			</div>
		</div>

	</div>

	<script src="<c:url value="/js/bootstrap.bundle.js"/>"></script>
</body>
</html>