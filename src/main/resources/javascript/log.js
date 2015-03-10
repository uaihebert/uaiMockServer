var app = angular.module('uaiMockServerApp', ['tableSort', 'angular-growl', 'ngAnimate', 'ui.bootstrap'], function($locationProvider){
    $locationProvider.html5Mode(true);
});

app.controller('webSocketController', function($scope, $location) {
    $scope.isCollapsed = true;

    $scope.connectionStatus = 'offline';
    $scope.displayOnlineText = false;
    $scope.webSocketLog = {};
    $scope.webSocketLog.logRequestList = [];
    $scope.connectToWebSocket = function() {
        if (window.WebSocket) {
            $scope.socket = new WebSocket("ws://localhost:1234/uai-mock-server-gui-ws");
            $scope.socket.onmessage = function (event) {
                $scope.$apply(function() {
                    var jsonObj = JSON.parse(event.data);

                    $scope.webSocketLog.logRequestList.push(jsonObj);
                });
            };
            $scope.socket.onopen = function (event) {
                $scope.$apply(function() {
                    $scope.connectionStatus = 'connected';
                    $scope.displayOnlineText = true;
                });
            };
            $scope.socket.onclose = function (event) {
                $scope.$apply(function() {
                    $scope.connectionStatus = 'offline';
                    $scope.displayOnlineText = false;
                });
            };
        } else {
            alert("Your browser does not support Websockets. (Use FireFox or Chrome)");
        }
    }

    $scope.disconnectToWebSocket = function() {
        $scope.socket.close();
    }

    $scope.connectToWebSocket();
    $scope.getCalloutRequestClass = function (log) {
        if (log.finishedWithError) {
            return 'bs-callout bs-callout-danger'
        }

        return 'bs-callout bs-callout-primary';
    };

    $scope.getResponseText = function (log) {
        if (log.finishedWithError) {
            return 'Nooooooooo'
        }

        return 'Yes (:';
    };

    $scope.getConnectionStatus = function () {
        return $scope.connectionStatus;
    }

    $scope.isActive = function(route) {
        if ($location.search()['fileName'].contains(route)) {
            return 'active';
        }

        if ($location.path().contains(route)) {
            return 'active';
        }

        return "";
    }
})