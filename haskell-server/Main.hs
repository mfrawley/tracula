{-# LANGUAGE OverloadedStrings #-}
import Web.Scotty

main = scotty 3000 $ do
  get "/" $ do
    json (["hello world"] :: [String])

  get "/tickets/:id" $ do
    id <- param "id"
    json ([id] :: [Int])

  notFound $ do
    text "there is no such route."