var app = angular.module('uaiMockServerApp', ['tableSort', 'angular-growl', 'ngAnimate', 'ui.bootstrap'], function($locationProvider){
    $locationProvider.html5Mode(true);
});

app.config(['growlProvider', function(growlProvider) {
    growlProvider.globalTimeToLive(2500);
}]);

app.controller('rootConfigController', function($scope, $location, $http, growl) {
    $scope.loadData = function(){
        $http.get('/uaiGui/rootConfigurations').
            success(function(data) {
                $scope.rootConfig = {};

                $scope.rootConfig.port = data.port;
                $scope.rootConfig.host = data.host;
                $scope.rootConfig.context = data.context;
                $scope.rootConfig.fileLog = data.fileLog;
                $scope.rootConfig.consoleLog = data.consoleLog;
                $scope.rootConfig.defaultContentType = data.defaultContentType;

                $scope.rootConfig.backUpStrategy = data.backUpStrategy;

                $scope.backUpStrategyList = data.backUpStrategyList;

                $scope.mainFilePath = data.uaiMainFile.fullPath;
            }
        ).error(function(){
                $scope.displayErrorGrowl();
            });
    }

    $scope.update = function () {
        $http.put('/uaiGui/rootConfigurations', $scope.rootConfig).
            success(function(data) {
                $scope.displaySuccessGrowl();
                $scope.loadData();
            }
        ).error(function(){
                $scope.displayErrorGrowl();
            });
    }

    $scope.isActive = function(route) {
        var queryParam = $location.search().fileName;
        if ($location.path().indexOf(route) > -1 || (queryParam != null && queryParam.indexOf(route) > -1)) {
            return 'active';
        }

        return "";
    }

    $scope.loadData();

    $scope.displaySuccessGrowl = function() {
        growl.addSuccessMessage("Operation Confirmed (:");
    }

    $scope.displayErrorGrowl = function() {
        growl.addErrorMessage("Check the log, something went wrong :(");
    }
})