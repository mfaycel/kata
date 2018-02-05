<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Compte Detail</title>
<style>
.username.ng-valid {
	background-color: lightgreen;
}

.username.ng-dirty.ng-invalid-required {
	background-color: red;
}

.username.ng-dirty.ng-invalid-minlength {
	background-color: yellow;
}

.email.ng-valid {
	background-color: lightgreen;
}

.email.ng-dirty.ng-invalid-required {
	background-color: red;
}

.email.ng-dirty.ng-invalid-email {
	background-color: yellow;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container" ng-controller="CompteController as ctrl">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">Compte Information </span>
			</div>
			<div class="formcontainer">
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="file">Name</label>
						<div class="col-md-7">
							<output ng-model="ctr.kataUser.name" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="file">compte iban</label>
						<div class="col-md-7">
							<output ng-model="ctr.kataCompte.bic" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label class="col-md-2 control-lable" for="file">solde</label>
						<div class="col-md-7">
							<output ng-model="ctr.kataCompte.balance" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Operations </span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID.</th>
							<th>montant</th>
							<th>operationType</th>
							<th>dateExcahnge</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.liKataOperations">
							<td><span ng-bind="u.id"></span></td>
							<td><span ng-bind="u.amount"></span></td>
							<td><span ng-bind="u.operationType"></span></td>
							<td><span ng-bind="u.dateExcahnge"></span></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
		 <script  src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-route.js"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/kata_compte_service.js' />"></script>
	<script
		src="<c:url value='/static/js/controller/kata_compte_controller.js' />"></script>
</body>
</html>