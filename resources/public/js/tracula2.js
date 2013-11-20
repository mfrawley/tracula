var replaceBold = (function(s) {
  return s.replace(/\'\'\'([\w\:\s\t]+)\'\'\'/ig, (function(g, l) {
    return ("<b>" + l + "</b>");
  }));
});

var replaceWikiLinks = (function(s) {
  return s.replace(/\[(.*?)\]/g, (function(m, l) {
    var p = l.split(/\:/);
    var linkBase = p(0);
    var linkPage = p(1);
    return ("<a href=\"" + Tracula.wikiUrl + linkPage + "\">" + linkPage + "</a>");
  }));
});


