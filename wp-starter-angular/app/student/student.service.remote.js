/**
 * Created by Bojan on 12/19/2016.
 */

(function (angular) {
  'use strict';

  angular
    .module('wp-angular-starter')
    .factory('StudentService', StudentServiceFn);

  StudentServiceFn.$inject = ['$log', '$timeout', '$q', '$resource'];

  /* @ngInject */
  function StudentServiceFn($log, $timeout, $q, $resource) {
    var studentList = [];
    var studentIdSequence = 0;
    var resource = $resource('http://localhost:8000/api/students/:id', {}, {
      update:{isArray:false, method:'PUT'}
    });
    var service = {
      save: saveFn,
      update: updateFn,
      getAll: getAllFn,
      remove: removeFn
    };


    return service;


    function saveFn(studentEntity) {
      if(studentEntity.id === undefined) {
        return resource.save(studentEntity, function(data){
          studentEntity.id=data.id;
        }).$promise;
      }
      return updateFn(studentEntity);
      $log.debug('in save fn');

    }

    function updateFn(studentEntity) {
      if (studentEntity.id === undefined) {
        $log.debug("IFFFF");
        return saveFn(studentEntity).$promise;
      }
      $log.debug("UPDATE");
      return resource.update({id: studentEntity.id}, studentEntity).$promise;
    }



    function getAllFn() {
      return resource.query().$promise;
    }

    function removeFn(studentEntity) {
      $log.debug("DELETE");
      return resource.delete({id:studentEntity.id}).$promise;
    }



  }

})(angular);

