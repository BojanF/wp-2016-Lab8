/**
 * Created by Bojan on 12/19/2016.
 */
(function (angular) {
  'use strict';

  angular
    .module('wp-angular-starter')
    .factory('AssociationService', AssociationServiceFn);

  AssociationServiceFn.$inject = ['$log', '$timeout', '$q', '$resource'];

  /* @ngInject */
  function AssociationServiceFn($log, $timeout, $q, $resource) {
    var studentList = [];
    var studentIdSequence = 0;
    var resourceCourses = $resource('http://localhost:8000/api/courses/:id', {}, {
      update:{isArray:false, method:'PUT'}
    });

    var resourceStudents = $resource('http://localhost:8000/api/students/:id', {}, {
      update:{isArray:false, method:'PUT'}
    });

    var service = {
      save: saveFn,
      update: updateFn,

      getAllCourses: getAllCoursesFn,
      getAllStudents: getAllStudentsFn,

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



    function getAllCoursesFn() {
      return resourceCourses.query().$promise;
    }

    function getAllStudentsFn(){
      return resourceStudents.query().$promise;
    }


  }

})(angular);

