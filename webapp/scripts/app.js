/**
 * ntishkevich
 * 09.11.2015
 */
(function(angular) {
    "use strict";
    angular.module('keyTrinket')
        .run(function ($rootScope, $location, $window, $http) {
            //$rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
            //    $rootScope.toState = toState;
            //    $rootScope.toStateParams = toStateParams;
            //
            //    if (Principal.isIdentityResolved()) {
            //        Auth.authorize();
            //    }
            //
            //});

            //$rootScope.$on('$stateChangeSuccess',  function(event, toState, toParams, fromState, fromParams) {
                //var titleKey = 'hipster' ;
                //
                //// Remember previous state unless we've been redirected to login or we've just
                //// reset the state memory after logout. If we're redirected to login, our
                //// previousState is already set in the authExpiredInterceptor. If we're going
                //// to login directly, we don't want to be sent to some previous state anyway
                //if (toState.name != 'login' && $rootScope.previousStateName) {
                //    $rootScope.previousStateName = fromState.name;
                //    $rootScope.previousStateParams = fromParams;
                //}
                //
                //// Set the page title key to the one configured in state or use default one
                //if (toState.data.pageTitle) {
                //    titleKey = toState.data.pageTitle;
                //}
                //$window.document.title = titleKey;
            //});

            $rootScope.back = function() {
                //// If previous state is 'activate' or do not exist go to 'home'
                //if ($rootScope.previousStateName === 'activate' || $state.get($rootScope.previousStateName) === null) {
                //    $state.go('home');
                //} else {
                //    $state.go($rootScope.previousStateName, $rootScope.previousStateParams);
                //}
            };
        })
        .config(function () {

            ////Cache everything except rest api requests
            //httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*api.*/, /.*protected.*/], true);
            //
            //$urlRouterProvider.otherwise('/');
            //$stateProvider.state('site', {
            //    'abstract': true,
            //    views: {
            //        'navbar@': {
            //            templateUrl: 'scripts/components/navbar/navbar.html',
            //            controller: 'NavbarController'
            //        }
            //    },
            //    resolve: {
            //        authorize: ['Auth',
            //            function (Auth) {
            //                return Auth.authorize();
            //            }
            //        ]
            //    }
            //});
            //
            //$httpProvider.interceptors.push('errorHandlerInterceptor');
            //$httpProvider.interceptors.push('authExpiredInterceptor');
            //$httpProvider.interceptors.push('authInterceptor');
            //$httpProvider.interceptors.push('notificationInterceptor');

        });

})(angular);
