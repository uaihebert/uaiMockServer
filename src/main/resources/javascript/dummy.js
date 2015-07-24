var app = angular.module('uaiMockServerApp', ['tableSort', 'angular-growl', 'ngAnimate', 'ui.bootstrap','ngClipboard'], function($locationProvider){
    $locationProvider.html5Mode(true);
});

app.controller('dummyController', function($scope, $http, growl, $location) {
    $scope.isActive = function(route) {
        var queryParam = $location.search().fileName;
        if ($location.path().indexOf(route) > -1 || (queryParam != null && queryParam.indexOf(route) > -1)) {
            return 'active';
        }

        return "";
    }

    $scope.selectedCardType = 'Visa';

    $scope.loadData = function(){
        $http.get('/uaiGui/dummy?cardType='+$scope.selectedCardType).
            success(function(data) {
                $scope.creditCardTypeList = data.supportedCreditCardList;

                $scope.generatedPanList = data.generatedPanList;
                $scope.generatedPanList.splice(0, 0, 'Number');

                $scope.generatedCvvList = data.generatedCvvList;
                $scope.generatedCvvList.splice(0, 0, 'CVV');

                $scope.generatedExpirationDateList = data.generatedExpirationDateList;
                $scope.generatedExpirationDateList.splice(0, 0, 'Date');
            }
        ).error(function(){
            $scope.displayErrorGrowl();
        });
    }


    $scope.loadData();

    $scope.getTextToCopy = function(obj){
        console.log(obj)
        return obj;
    };
});