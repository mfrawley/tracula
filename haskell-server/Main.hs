{-# LANGUAGE OverloadedStrings #-}
import qualified Web.Scotty as S
import qualified Text.Blaze.Html5 as H
import qualified Text.Blaze.Html5.Attributes as A
import Text.Blaze.Html.Renderer.Text as T
--import qualified Tracula.IndexView
blaze = S.html . T.renderHtml

main = S.scotty 3000 $ do
  S.get "/" $ do
    --blaze Tracula.IndexView.render
    blaze $ do
      H.docTypeHtml $ do
        H.head $ do
          H.title "Miaow"
        H.body $ do
          H.h1 "My todo list"
          H.div "" H.! A.class_ "ds"

  S.get "/tickets/:id" $ do
    id <- S.param "id"
    S.json ([id] :: [Int])

  S.notFound $ do
    S.text "there is no such route."