import Web.Scotty
import Text.Blaze.Html5
import Text.Blaze.Html5.Attributes
import qualified Web.Scotty as S
import qualified Text.Blaze.Html5 as H
import qualified Text.Blaze.Html5.Attributes as A

module Tracula.IndexView where

render = do
  html $ do
    body $ do
      h1 "My todo list"
      ul $ do
        li "learn haskell"
        li "make a website"