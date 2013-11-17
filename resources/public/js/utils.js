String.prototype.replaceAll = function(str, replacement) {
  var regex;
  regex = new RegExp(str, "ig");
  return this.replace(regex, replacement);
};