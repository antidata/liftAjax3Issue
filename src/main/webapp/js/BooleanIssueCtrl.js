function BooleanIssueCtrl($scope) {

  $scope.setBool = function(value) {
    var promise = pageFunctions.booleanIssue({bool : value});

    promise.then(function(data) {
        alert('Success with value: ' + data);
    });

    promise.fail(function(data) {
        alert('Execution failed');
    });
  };
}