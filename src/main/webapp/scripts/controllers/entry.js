angular.module('wwwApp').controller('EntryCtrl', ['$scope', '$routeParams', '$location',
    'MoodService', 'LocationService', 'SecurityService', 'EntryService', 'LoginService',
    function ($scope, $routeParams, $location, MoodService, LocationService, SecurityService, EntryService, LoginService) {
        'use strict';

        $scope.login = LoginService.get();
        $scope.moods = MoodService.query();
        $scope.locations = LocationService.query();
        $scope.security = SecurityService.query();
        $scope.entry = {
            allowComments: true,
            autoFormat: true,
            // date: new Date(), // TODO: is this the right format?
            emailComments: true,
            subject: '',
            body: '',
            tag: '',
            music: '',
            trackback: ''
        };
        $scope.ErrorMessage = '';

        $scope.cancel = function () {
            // TODO: what about angular stuff?
            $scope.UpdateJournal.$setPristine();
            $scope.entry = {
                allowComments: true,
                autoFormat: true,
                // date: new Date(), // TODO: is this the right format?
                emailComments: true,
                subject: '',
                body: '',
                tag: '',
                music: '',
                trackback: ''
            };
        };

        $scope.save = function () {
            if (jQuery('form#frmUpdateJournal').valid()) {

                if ($scope.entry.mood.id !== 'undefined')
                    $scope.entry.moodId = $scope.entry.mood.id;

                if ($scope.entry.location.id !== 'undefined')
                    $scope.entry.locationId = $scope.entry.location.id;

                if ($scope.entry.security.id !== 'undefined')
                    $scope.entry.securityId = $scope.entry.security.id;

                // EDIT case
                if (typeof $routeParams.entryId !== 'undefined') {
                    EntryService.update($scope.entry, function success() {
                                alert('Blog Entry Updated');
                                $location.path('/users/' + $scope.login.username);
                            },
                            function fail(response) {
                                if (typeof(response.data.ModelState) !== 'undefined') {
                                    $scope.ErrorMessage = response.data.Message + ' (' + response.status + ') ' + angular.toJson(response.data.ModelState);
                                }
                                else if (typeof(response.data.ExceptionMessage) !== 'undefined') {
                                    $scope.ErrorMessage = response.data.Message + ' (' + response.status + ') ' + response.data.ExceptionMessage + ' ' + response.data.ExceptionType + ' ' + response.data.StackTrace;
                                }
                                else {
                                    $scope.ErrorMessage = 'Unknown error occurred. Response was ' + angular.toJson(response);
                                }
                            }
                    );
                    return false;
                }

                EntryService.save($scope.entry, function success() {
                            alert('Blog Entry Posted');
                            $location.path('/users/' + $scope.login.username);
                        },
                        function fail(response) {
                            if (typeof(response.data.ModelState) !== 'undefined') {
                                $scope.ErrorMessage = response.data.Message + ' (' + response.status + ') ' + angular.toJson(response.data.ModelState);
                            }
                            else if (typeof(response.data.ExceptionMessage) !== 'undefined') {
                                $scope.ErrorMessage = response.data.Message + ' (' + response.status + ') ' + response.data.ExceptionMessage + ' ' + response.data.ExceptionType + ' ' + response.data.StackTrace;
                            }
                            else {
                                $scope.ErrorMessage = 'Unknown error occurred. Response was ' + angular.toJson(response);
                            }
                        }
                );
            }
            return false;
        };

        $scope.$on('$destroy', function finalize() {
            $scope.moods = null;
            $scope.locations = null;
            $scope.security = null;
            $scope.entry = null;
            $scope.ErrorMessage = null;
        });

        $scope.init = function () {
            jQuery("#frmUpdateJournal").validate();

            jQuery('#tags').bind('change', function () {
                $(this).value = $(this).value.toLocaleLowerCase();
            });

            if (typeof $routeParams.entryId !== 'undefined') {
                $scope.entry = EntryService.get({Id: $routeParams.entryId});
            }
        };
        $scope.init();
    }]);