# E-HEALTH_PROJECT

This README explains how the directories work and what you could find if you check every directory step by step

 * under data management folder you could see the data science notebooks and the data preparation scripts, and also local README.md

The queries for MongoDB are the following:
 * Filter by app naming:
  ```
  db.e_health_pubmed.findOne({"app" : "University of Alabama"})
  ```
 * Filter by category:
  ```
  db.e_health_pubmed.aggregate([{$unwind: "$articles"}, {$match: {"articles.keywords": {$all: ["games"] }}}])
  ```
 * Get distinct categories:
  ```
  db.e_health_pubmed.aggregate([
    {$unwind: "$articles"},
    {
      $project: {
        _id: 0,
        keys: "$articles.keywords"
      }
    },
    {
      $group: {
        _id: null,
        uniqueKeys: {$push: "$keys"}
      }
    },
    {
      $project: {
        selectedKeys: {
          $reduce: {
            input: "$uniqueKeys",
            initialValue: [],
            in: {$setUnion: ["$$value", "$$this"]}
          }
        }
      }
    }
  ])
  ```
