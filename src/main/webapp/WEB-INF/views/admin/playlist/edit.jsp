<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<style>
    .error {
        color: #cc5965;
        display: inline-block;
        padding-left: 5px;
    }
    .success {
    	padding-top: 7px;
    	margin-bottom: 0;
    	color: #1ab394;
    }
    .failed {
    	padding-top: 7px;
    	margin-bottom: 0;
    	color: #ed5565;
    }
</style>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-8">
        <h2>Edit Playlist details</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/playlist/"/>">Playlist Management</a></li>
            <li class="active"><strong>Playlist edit</strong></li>
        </ol>
    </div>
    <div class="col-lg-4">
        <div class="title-action">
            <a href="<spring:url value="list "/>" class="btn btn-white">Go Back</a>
            <a href="<spring:url value="details-${playlist.id}"/>" class="btn btn-info">View Details</a>
            <a href="<spring:url value="delete-${playlist.id}"/>" class="btn btn-danger delete-playlist">Delete</a>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight ecommerce">

    <div class="row">
        <div class="col-lg-12">
            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#tab-1">Playlist Info</a></li>
                </ul>

                <div class="tab-content">

                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">

                            <form:form id="editPlaylistForm" method="POST" modelAttribute="playlist" class="form-horizontal">
                                <form:hidden path="id" />
								<form:hidden path="imageUrl" />
			                    
                                <fieldset class="form-horizontal">
                                	<div class="form-group">
                                		<label class="col-lg-8">
                                			<c:if test="${success != null}">
                                				<p class="success">
                                					<c:out value="${success}" />
                                				</p>
                                			</c:if>
                                			<c:if test="${failed != null}">
                                				<p class="failed">
                                					<c:out value="${failed}" />
                                				</p>
                                			</c:if>
                                		</label>
                                    	<div class="col-lg-4" style="text-align: right">
                                       		<input type="submit" name="save" class="btn btn-primary" value="Save and Continue" />
                							<input type="submit" name="save" class="btn btn-success" value="Save" />
                                    	</div>
                                	</div>
                                
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Name</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="name" class="form-control" placeholder="Playlist name" />
                                            <form:errors path="name" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Total Views</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="totalViews" class="form-control" placeholder="Total Views" />
                                            <form:errors path="totalViews" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Artist</label>
                                        <div class="col-lg-4">
                                            <form:select path="artist" items="${artists}" itemValue="id" itemLabel="name" class="form-control" />
                                            <form:errors path="artist" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Genre</label>
                                        <div class="col-lg-4">
                                            <form:select path="genre" items="${genres}" itemValue="id" itemLabel="name" class="form-control" />
                                            <form:errors path="genre" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Playlist Type</label>
                                        <div class="col-lg-4">
                                            <form:select path="playlistType" items="${pltypes}" itemValue="id" itemLabel="name" class="form-control" />
                                            <form:errors path="playlistType" cssClass="error" />
                                        </div>
                                    </div>
                                </fieldset>
                                
                            </form:form>

                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>

</div>

<script>
    $(document).ready(function() {
    	$('.delete-playlist').click(function(e) {
    	    e.preventDefault();
    	    var href = $(this).attr("href");
    	    swal({
    	            title: "Are you sure?",
    	            text: "You will not be able to recover this playlist!",
    	            type: "warning",
    	            showCancelButton: true,
    	            confirmButtonColor: "#DD6B55",
    	            confirmButtonText: "Yes, delete it!",
    	            closeOnConfirm: false
    	        },
    	        function() {
                	$.get(href,function(){
                    	swal("Deleted!", "Playlist has been deleted.", "success");
                    	window.location.href = "list";
                	}).fail(function(){
                		swal("Error", "Playlist could not be deleted", "error");
                	});
                });
    	});

        $("#editPlaylistForm").validate({
            rules: {
                name: {
                    required: true
                },
                totalViews: {
                    required: false,
                    number: true
                }
            }
        });
        
    });
</script>