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
        <h2>Edit Song details</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/song/"/>">Song Management</a></li>
            <li class="active"><strong>Song edit</strong></li>
        </ol>
    </div>
    <div class="col-lg-4">
        <div class="title-action">
            <a href="<spring:url value="list "/>" class="btn btn-white">Go Back</a>
            <a href="<spring:url value="details-${song.id}"/>" class="btn btn-info">View Details</a>
            <a href="<spring:url value="delete-${song.id}"/>" class="btn btn-danger delete-song">Delete</a>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight ecommerce">

    <div class="row">
        <div class="col-lg-12">
            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#tab-1">Song Info</a></li>
                    <li class=""><a data-toggle="tab" href="#tab-2">Artist mappings</a></li>
                    <li class=""><a data-toggle="tab" href="#tab-2">Playlist mappings</a></li>
                </ul>

                <div class="tab-content">

                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">

                            <form:form id="editSongForm" method="POST" modelAttribute="song" enctype="multipart/form-data" class="form-horizontal">
                                <form:hidden path="id" />
			                    
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
                                            <form:input type="text" path="name" class="form-control" placeholder="Song name" />
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
                                        <label class="col-sm-2 control-label">Week Views</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="weekViews" class="form-control" placeholder="Week Views" />
                                            <form:errors path="weekViews" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Description</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="description" class="form-control" placeholder="Description" />
                                            <form:errors path="description" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Genre</label>
                                        <div class="col-lg-4">
                                            <form:select path="genre" class="form-control">
                                            	<form:option label="" value="" />
                                            	<form:options items="${genres}" itemLabel="name" itemValue="id" />
                                            </form:select>
                                            <form:errors path="genre" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">File Url</label>
                                        <div class="col-sm-10">
                                        	<div class="input-group m-b">
                                        		<span class="input-group-addon"> <input name="resource" value="url" type="radio" checked> </span> <form:input type="text" path="url" class="form-control" placeholder="File Url" />
											</div>
											<form:errors path="url" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Upload to<br/><small class="text-navy">Application</small></label>
                                        <div class="col-sm-10">
											<div class="input-group">
                                        		<span class="input-group-addon"> <input name="resource" value="application" type="radio"> </span> <input type="file" id="appFile" name="appFile" class="form-control" accept="audio/*">
											</div>
											<c:if test="${appFileError != null}">
                                        		<span class="error"><c:out value="${appFileError}" /></span>
                                        	</c:if>
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Upload to<br/><small class="text-navy">Google Drive</small></label>
                                        <div class="col-sm-10">
											<div class="input-group">
                                        		<span class="input-group-addon"> <input name="resource" value="google" type="radio"> </span> <input type="file" id="gFile" name="gFile" class="form-control" accept="audio/*">
											</div>
											<c:if test="${gFileError != null}">
                                        		<span class="error"><c:out value="${gFileError}" /></span>
                                        	</c:if>
                                        </div>
                                    </div>
                                    
                                </fieldset>
                            </form:form>

                        </div>
                    </div>

					<div id="tab-2" class="tab-pane">
                        <div class="panel-body">

                            //do something here

                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>

</div>

<script>
    $(document).ready(function() {
    	$('.delete-song').click(function(e) {
    	    e.preventDefault();
    	    var href = $(this).attr("href");
    	    swal({
    	            title: "Are you sure?",
    	            text: "You will not be able to recover this song!",
    	            type: "warning",
    	            showCancelButton: true,
    	            confirmButtonColor: "#DD6B55",
    	            confirmButtonText: "Yes, delete it!",
    	            closeOnConfirm: false
    	        },
    	        function() {
                	$.get(href,function(){
                    	swal("Deleted!", "Song has been deleted.", "success");
                    	window.location.href = "list";
                	}).fail(function(){
                		swal("Error", "Song could not be deleted", "error");
                	});
                });
    	});

        $("#editSongForm").validate({
            rules: {
                name: {
                    required: true
                },
                totalViews: {
                    required: false,
                    number: true
                },
                weekViews: {
                    required: false,
                    number: true
                }
            }
        });
        
    });
</script>