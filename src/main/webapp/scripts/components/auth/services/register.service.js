'use strict';

angular.module('keytrinket')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


