import pandas as pd
import json
import time


"""
This script works as the following:
It parses the data that has been analyzed and preparing it for insertion to MongoDB
To run it you need to have the file SiteDataset.csv in the same folder
"""

def preprocess(file_path):
    df = pd.read_csv('')
    files = []
    for _, value in df.iterrows():
        existed = False
        index = -1
        for app in files:
            index += 1
            if value.appnames in app['app']:
                existed = True
                break

        if existed:
            keywords = []
            for i in value.keywords[2:-2].split("', '"):
                keywords.append(i)
                
            files[index]['articles'].append({
                'pubmed_id': value.pubmed_id,
                'title': value.title,
                'abstract': value.abstract,
                'class': value['class'],
                'keywords': keywords})
        else:
            keywords = []
            for i in value.keywords[2:-2].split("', '"):
                keywords.append(i)

            files.append(
                {
                    'app': value.appnames,
                    'articles': [{
                        'pubmed_id': value.pubmed_id,
                        'title': value.title,
                        'abstract': value.abstract,
                        'class': value['class'],
                        'keywords': keywords
                    }]
                }
            )
        for file in files:
            with open('./DBdata/data_{}.json'.format(round(time.time() * 1000)), 'w') as fp:
                json.dump(file, fp)


if __name__ == "__main__":
    preprocess('./SiteDataset.csv')
