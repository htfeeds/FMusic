<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<style>
    .error {
        color: #cc5965;
        display: inline-block;
        padding-left: 5px;
    }
</style>

<form:form id="playlistForm" method="POST" modelAttribute="playlist" enctype="multipart/form-data" class="form-horizontal">
    <form:hidden path="id" />

    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-8">
            <h2>Create a new Playlist</h2>
            <ol class="breadcrumb">
                <li><a href="<spring:url value="/admin/"/>">Home</a></li>
                <li><a href="<spring:url value="/admin/playlist/"/>">Playlist Management</a></li>
                <li class="active"><strong>Create Playlist</strong></li>
            </ol>
        </div>
        <div class="col-lg-4">
            <div class="title-action">
                <a href="<spring:url value="list"/>" class="btn btn-white">Go Back</a>
                <input type="submit" name="save" class="btn btn-primary" value="Create and Edit" />
                <input type="submit" name="save" class="btn btn-success" value="Create" />
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

                                <fieldset class="form-horizontal">
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
                                        <label class="col-sm-2 control-label">Image</label>
                                        <div class="col-sm-10">
                                            <input type="file" id="image" name="image" class="form-control" accept="image/*"/>
                                            <c:if test="${imgError != null}">
                                        		<span class="error"><c:out value="${imgError}" /></span>
                                        	</c:if>
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
                                
                            </div>
                        </div>
                        
                    </div>
                    
                </div>
            </div>
        </div>

    </div>

</form:form>

<script>
    $(document).ready(function() {

        $("#playlistForm").validate({
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