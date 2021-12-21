for filename in *; do mongoimport --uri "mongodb+srv://root:password1234*qwerty@cluster0.delkk.mongodb.net/e_health_data" --collection e_health_pubmed --file $filename; done
