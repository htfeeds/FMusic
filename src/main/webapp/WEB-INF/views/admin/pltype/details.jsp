<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-8">
        <h2>Playlist Type Details</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/pltype/"/>">Playlist Type Management</a></li>
            <li class="active"><strong>Playlist Type details</strong></li>
        </ol>
    </div>
    <div class="col-lg-4">
        <div class="title-action">
            <a href="<spring:url value="list"/>" class="btn btn-white">Go back</a>
            <a href="<spring:url value="edit-${playlistType.id}"/>" class="btn btn-warning">Edit</a>
            <a href="<spring:url value="delete-${playlistType.id}"/>" class="btn btn-danger delete-playlistType">Delete</a>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight ecommerce">

    <div class="row">
        <div class="col-lg-12">
            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#tab-1">PlType Info</a></li>
                </ul>
                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">

                            <fieldset class="form-horizontal">

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Id</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${playlistType.id}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Name</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${playlistType.name}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Description</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <c:out value="${playlistType.description}" />
                                        </p>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Date added</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <fmt:formatDate type="both" value="${playlistType.creationTime}" />
                                        </p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Date modified</label>
                                    <div class="col-lg-10">
                                        <p class="form-control-static">
                                            <fmt:formatDate type="both" value="${playlistType.modificationTime}" />
                                        </p>
                                    </div>
                                </div>

                            </fieldset>
                            
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>

<script>
$(document).ready(function() {
	$('.delete-playlistType').click(function(e) {
	    e.preventDefault();
	    var href = $(this).attr("href");
	    swal({
	            title: "Are you sure?",
	            text: "You will not be able to recover this playlistType!",
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#DD6B55",
	            confirmButtonText: "Yes, delete it!",
	            closeOnConfirm: false
	        },
	        function() {
            	$.get(href,function(){
                	swal("Deleted!", "Playlist Type has been deleted.", "success");
                	window.location.href = "list";
            	}).fail(function(){
            		swal("Error", "Playlist Type could not be deleted", "error");
            	});
            });
	});
});
</script>