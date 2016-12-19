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
      /*getById: getByIdFn,*/
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

    /*function getByIdFn(studentId) {
      var index;
      var deferred = $q.defer();
      $timeout(function () {
        $log.debug('get by id: ', studentId);
        index = findIndexById(studentId);
        if (index === -1) {
          deferred.resolve(null);
        } else {
          deferred.resolve(studentList[index]);
        }
      }, 100);
      return deferred.promise;
    }*/

    function getAllFn() {
      return resource.query().$promise;
    }

    function removeFn(studentEntity) {
      $log.debug("DELETE");
      return resource.delete({id:studentEntity.id}).$promise;
    }

    /*function findIndexById(studentId) {
      var result = -1, item;

      $log.debug('get index by id: ', studentId);
      for (var i = 0; i < studentList.length; i++) {
        item = studentList[i];
        if (item.id === studentId) {
          result = i;
          break;
        }
      }
      return result;
    }*/

  }

})(angular);

