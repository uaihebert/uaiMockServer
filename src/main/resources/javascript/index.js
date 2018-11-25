const app = angular.module('uaiMockServerApp', ['tableSort', 'angular-growl', 'ngAnimate'], function ($locationProvider) {
    $locationProvider.html5Mode(true);
});

app.config(['growlProvider', function(growlProvider) {
    growlProvider.globalTimeToLive(2500);
}]);

app.controller('routeController', function($scope, $http, growl, $location) {
    $scope.currentProject = "";

    $scope.loadTable = function(selectedProject){
        $scope.isCollapsed = true;

        $scope.projectFilter = "";

        if (selectedProject != null && selectedProject !== "") {
            $scope.currentProject = selectedProject;
        }

        if ($scope.currentProject !== "") {
            $scope.projectFilter = "?selectedProject=" + $scope.currentProject;
        }

        $http.get('/uaiGui/uaiRoute' + $scope.projectFilter).
            success(function(data) {
                $scope.routeRowList = [];
                $scope.rootConfiguration = data.rootConfiguration;
                $scope.httpMethodArray = data.httpMethodArray;
                $scope.bodyValidationTypeList = data.bodyValidationTypeList;

                $scope.projectList = [];

                if ($scope.currentProject === "") {
                    $scope.currentProject = data.defaultProject;
                }

                $scope.projectList.push(data.defaultProject);

                if (data.projectList != null && data.projectList.length > 0) {
                    $scope.projectList =  $scope.projectList.concat(data.projectList);
                }

                for (let i = 0; i < data.routeList.length; i++) {
                    let routeRow = {};

                    routeRow.requestPath = data.routeList[i].request.path;
                    routeRow.responseCode = data.routeList[i].response.statusCode;
                    routeRow.requestName = data.routeList[i].request.name;
                    routeRow.requestMethod = data.routeList[i].request.method;
                    routeRow.responseContentType = data.routeList[i].response.contentType;

                    routeRow.route = data.routeList[i];

                    let request = routeRow.route.request;

                    request.headerList = [];

                    if (request.requiredHeaderList) {
                        for (let j = 0; j < request.requiredHeaderList.length; j++) {
                            const requiredHeader = request.requiredHeaderList[j];
                            requiredHeader.required = true;

                            request.headerList.push(requiredHeader);
                        }
                    }

                    if (request.optionalHeaderList) {
                        for (let k = 0; k < request.optionalHeaderList.length; k++) {
                            const optionalHeader = request.optionalHeaderList[k];
                            optionalHeader.required = false;

                            request.headerList.push(optionalHeader);
                        }
                    }
                    
                    request.queryParamList = [];

                    if (request.requiredQueryParamList) {
                        for (let l = 0; l < request.requiredQueryParamList.length; l++) {
                            const requiredQueryParam = request.requiredQueryParamList[l];
                            requiredQueryParam.required = true;

                            request.queryParamList.push(requiredQueryParam);
                        }
                    }

                    if (request.optionalQueryParamList) {
                        for (let m = 0; m < request.optionalQueryParamList.length; m++) {
                            const optionalQueryParam = request.optionalQueryParamList[m];
                            optionalQueryParam.required = false;

                            request.queryParamList.push(optionalQueryParam);
                        }
                    }

                    $scope.routeRowList.push(routeRow);
                }
            }
        ).error(function(){
                $scope.displayErrorGrowl();
        });
    };

    $scope.loadTable();

    $scope.hasErrorInList = function(type , listToCheck, error) {
        if (listToCheck == null) {
            return false;
        }

        for (let i = 0; i < listToCheck.length; i++) {
            const list = listToCheck[i];
            if (list.name === "" && list.valueList != null && list.valueList.length > 0) {
                error.hasError = true;
                error.errorHtml += "<li>There is a value of " + type + " created, but has not name: " + list.valueList +" </li>";
            }

            if ((list.name != null && list.name.trim() !== "") && (list.valueList === null || list.valueList.length === 0)) {
                error.hasError = true;
                error.errorHtml += "<li>There is a value of " + type + " with the name: " + list.name + " but it has no value</li>";
            }
        }
    };

    $scope.getErrorText = function(route) {
        const error = {};
        error.hasError = false;
        error.errorHtml = "<ul>";

        if (route.request.name == null || route.request.name.trim() === "") {
            error.hasError = true;
            error.errorHtml += "<li>request.name was not found</li>";
        }

        if (route.request.path == null || route.request.path.trim() === "") {
            error.hasError = true;
            error.errorHtml += "<li>request.path was not found</li>";
        }

        if (route.request.path != null && route.request.path.indexOf("/") !== 0) {
            error.hasError = true;
            error.errorHtml += "<li>request.path must start with /</li>";
        }

        if (route.request.method == null || route.request.method.trim() === "") {
            error.hasError = true;
            error.errorHtml += "<li>request.method was not found</li>";
        }

        if (route.response.statusCode == null || route.response.statusCode <= 0) {
            error.hasError = true;
            error.errorHtml += "<li>response.statusCode was not found</li>";
        }

        if (route.response.contentType === null || route.response.contentType.trim() === "") {
            error.hasError = true;
            error.errorHtml += "<li>response.contentType was not found</li>";
        }

        $scope.hasErrorInList("Request ---> HeaderList", route.request.requiredHeaderList, error);
        $scope.hasErrorInList("Request ---> HeaderList", route.request.optionalHeaderList, error);
        $scope.hasErrorInList("Request ---> QueryParamList", route.request.requiredQueryParamList, error);
        $scope.hasErrorInList("Request ---> QueryParamList", route.request.optionalQueryParamList, error);

        $scope.hasErrorInList("Response ---> HeaderList", route.response.headerList, error);

        let callback = route.callback;

        if (callback !== undefined && callback !== null) {
            if (!Number.isInteger(callback.delayInMilli)) {
                error.hasError = true;
                error.errorHtml += "<li>callback.delayInMilli should be a valid int</li>";
            } else if (callback.delayInMilli < 0 ) {
                error.hasError = true;
                error.errorHtml += "<li>callback.delayInMilli should be >= 0</li>";
            }

            if (callback.completeUrlToCall === null || callback.completeUrlToCall.trim() === "") {
                error.hasError = true;
                error.errorHtml += "<li>callback.completeUrlToCall was not found</li>";
            }

            //completeUrlToCall

            $scope.hasErrorInList("Callback ---> HeaderList", callback.headerList, error);
            $scope.hasErrorInList("Callback ---> QueryParamList", callback.queryParamList, error);
        }

        if (error.hasError) {
            error.errorHtml += "</ul>";
            return error.errorHtml;
        }

        return null;
    };

    $scope.finishWithSuccess = function() {
        $scope.loadTable();
        $('#routeModal').modal('toggle');
        $scope.displaySuccessGrowl();
    };

    $scope.saveRoute = function() {
        $scope.selectedRouteRow.route.request.requiredHeaderList = [];
        $scope.selectedRouteRow.route.request.optionalHeaderList = [];

        const headerList = $scope.convertStringToList($scope.selectedRouteRow.route.request.headerList);

        if (headerList) {
            for (let i = 0; i < headerList.length; i++) {
                const header = headerList[i];
                if (header.required) {
                    $scope.selectedRouteRow.route.request.requiredHeaderList.push(header);
                } else {
                    $scope.selectedRouteRow.route.request.optionalHeaderList.push(header);
                }
            }
        }

        $scope.selectedRouteRow.route.request.requiredQueryParamList = [];
        $scope.selectedRouteRow.route.request.optionalQueryParamList = [];

        const queryParamList = $scope.convertStringToList($scope.selectedRouteRow.route.request.queryParamList);

        if (queryParamList) {
            for (let j = 0; j < queryParamList.length; j++) {
                const queryParam = queryParamList[j];
                if (queryParam.required) {
                    $scope.selectedRouteRow.route.request.requiredQueryParamList.push(queryParam);
                } else {
                    $scope.selectedRouteRow.route.request.optionalQueryParamList.push(queryParam);
                }
            }
        }

        $scope.selectedRouteRow.route.response.headerList = $scope.convertStringToList($scope.selectedRouteRow.route.response.headerList);

        let callback = $scope.selectedRouteRow.route.callback;

        if (callback !== undefined && callback !== null) {
            callback.headerList = $scope.convertStringToList(callback.headerList);
            callback.queryParamList = $scope.convertStringToList(callback.queryParamList);
        }

        const errorText = $scope.getErrorText($scope.selectedRouteRow.route);

        if (errorText != null) {
            $scope.hasValidationErrors = true;
            $('#errorDiv').html(errorText);
            return;
        }

        if ($scope.action === 'update') {
            $http.put('/uaiGui/uaiRoute', $scope.selectedRouteRow.route).
                success(function(data) {
                    $scope.finishWithSuccess();
                }
            ).error(function(){
                    $scope.displayErrorGrowl();
                });
        } else {
            $http.post('/uaiGui/uaiRoute', $scope.selectedRouteRow.route).
                success(function(data) {
                    $scope.finishWithSuccess();
                }
            ).error(function(){
                    $scope.displayErrorGrowl();
                });
        }
    };

    $scope.displaySuccessGrowl = function() {
        growl.addSuccessMessage("Operation Confirmed (:");
    };

    $scope.displayErrorGrowl = function() {
        growl.addErrorMessage("Check the log, something went wrong :(");
    };

    $scope.convertStringToList = function(requiredHeaderList) {
        if (requiredHeaderList == null) {
            return;
        }

        const listTrimmed = [];

        for (let i = 0; i < requiredHeaderList.length; i++) {
            if (requiredHeaderList[i].name != null && requiredHeaderList[i].name.trim() === "") {
                if (requiredHeaderList[i].valueList == null || requiredHeaderList[i].valueList.length === 0) {
                    continue;
                }
            }

            if (typeof requiredHeaderList[i].valueList === "string") {
                requiredHeaderList[i].valueList = requiredHeaderList[i].valueList.split(",");
            }

            listTrimmed.push(requiredHeaderList[i]);
        }

        return listTrimmed;
    };

    $scope.displaySelected = function (routeRow) {
        $scope.hasValidationErrors = false;
        $scope.action = 'update';

        $scope.selectedRouteRow = {};
        angular.copy(routeRow, $scope.selectedRouteRow);

        $('#myTab a:first').tab('show');
        $('#routeModal').modal('toggle');
    };

    $scope.displayCreateNew = function () {
        $scope.action = 'create';
        $scope.hasValidationErrors = false;
        $scope.selectedRouteRow = {};
        $scope.selectedRouteRow.route = {};
        $scope.selectedRouteRow.route.request = {};
        $scope.selectedRouteRow.route.request.headerList = [];
        $scope.selectedRouteRow.route.request.queryParamList = [];
        $scope.selectedRouteRow.route.request.path = "/";
        $scope.selectedRouteRow.route.response = {};
        $scope.selectedRouteRow.route.response.contentType = $scope.rootConfiguration.defaultContentType;
        $scope.selectedRouteRow.route.uaiFile = {};
        $scope.selectedRouteRow.route.uaiFile.fullPath = $scope.rootConfiguration.uaiMainFile.fullPath;

        $('#myTab a:first').tab('show');
        $('#routeModal').modal('toggle');
    };

    $scope.deleteSelectedConfirmation = function () {
        $scope.action = 'delete';
        $('#routeModal').modal('toggle');
        $('#deleteRouteModal').modal('toggle');
    };

    $scope.cancelDelete = function () {
        $('#deleteRouteModal').modal('toggle');
        $('#routeModal').modal('toggle');
    };

    $scope.addNewHeaderRequest = function (request) {
        request.headerList.push({name:"", required: true, valueList: []});
    };

    $scope.addNewHeaderCallback = function (callback) {
        callback.headerList.push({name:"", required: true, valueList: []});
    };

    $scope.addNewHeaderResponse = function (response) {
        if (response.headerList == null) {
            response.headerList = [];
        }

        response.headerList.push({name:"", valueList: []});
    };

    $scope.addNewQueryParamRequest = function (request) {
        request.queryParamList.push({name:"", required: true, valueList: []});
    };

    $scope.addNewQueryParamCallback = function (callback) {
        callback.queryParamList.push({name:"", required: true, valueList: []});
    };

    $scope.delete = function () {
        $http.delete('/uaiGui/uaiRoute?routeId='+$scope.selectedRouteRow.route.id).
            success(function(data) {
                $scope.loadTable();
                $('#deleteRouteModal').modal('toggle');
                $scope.displaySuccessGrowl();
            }
        ).error(function(){
                $scope.displayErrorGrowl();
            });
    };

    $scope.isActive = function(route) {
        if ($location.url().indexOf(route) > -1) {
            return 'active';
        }

        return "";
    };

    $scope.cloneIt = function($event, routeRow) {
        $event.stopPropagation();

        $http.post('/uaiGui/uaiRoute/clone?routeId='+routeRow.route.id).
            success(function(data) {
                $scope.loadTable();
                $scope.displaySuccessGrowl();
            }
        ).error(function(){
                $scope.displayErrorGrowl();
            });
    };

    $scope.bodyRequired = function() {
        if (!$scope.selectedRouteRow.route.request.isBodyRequired) {
            return;
        }

        if ($scope.selectedRouteRow.route.request.bodyValidationType == null) {
            $scope.selectedRouteRow.route.request.bodyValidationType = 'VALIDATE_IF_PRESENT_ONLY'
        }
    };

    $scope.displayRequestBody = function() {
        if ($scope.selectedRouteRow == null) {
            return false;
        }

        return $scope.selectedRouteRow.route.request.bodyValidationType !== null &&
               $scope.selectedRouteRow.route.request.bodyValidationType !== "" &&
               $scope.selectedRouteRow.route.request.bodyValidationType !== 'VALIDATE_IF_PRESENT_ONLY'
    };

    $scope.addWildCard = function(object) {
        object.valueList = "UAI_*"
    }
});