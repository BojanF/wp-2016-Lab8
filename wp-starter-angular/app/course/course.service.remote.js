/**
 * Created by Bojan on 12/19/2016.
 */

(function (angular) {
  'use strict';

  angular
    .module('wp-angular-starter')
    .factory('CourseService', CourseServiceFn);

  CourseServiceFn.$inject = ['$log', '$resource'];

  /* @ngInject */
  function CourseServiceFn($log, $resource) {
    var groupsList = [];
    var groupIdSequence = 0;
    var resource = $resource('http://localhost:8000/api/courses/:id', {}, {
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


    function saveFn(courseEntity) {
      if(courseEntity.id === undefined) {
        return resource.save(courseEntity, function(data){
          courseEntity.id=data.id;
        }).$promise;
      }
      return updateFn(courseEntity);


    }

    function updateFn(courseEntity) {
      if (courseEntity.id === undefined) {
        $log.debug("IFFFF");
        return saveFn(courseEntity).$promise;
      }
      $log.debug("UPDATE");
      return resource.update({id: courseEntity.id}, courseEntity, null).$promise;

    }

    /*function getByIdFn(groupId) {
      return resource.get({id:groupId}).$promise;

    }*/

    function getAllFn() {
      return resource.query().$promise;
    }

    function removeFn(courseEntity) {
      $log.debug("DELETE");
      return resource.delete({id:courseEntity.id}).$promise;
    }
  }

})(angular);

