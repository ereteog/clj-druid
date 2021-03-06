(ns clj-druid.client-test
  (:require [clojure.test :refer :all]
            [clj-druid.validations-test :as f]
            [clj-druid.client :refer :all]))

(deftest test-connect-zookeeper
  (connect {:zk {:host "127.0.0.1:2181"
                 :discovery-path "/druid/discovery"
                 :node-type "broker"}}))

(deftest test-connect-user
  (connect {:hosts ["http://localhost:8082/druid/v2/"]}))

(deftest test-zk-query
  (connect {:zk {:host "127.0.0.1:2181"
                 :discovery-path "/druid/discovery"
                 :node-type "broker"}})

  (query randomized :groupBy f/valid-groupby-query))

(deftest test-user-query
  (connect {:hosts ["http://127.0.0.1:8083/druid/v2/"]})
  (query randomized :groupBy f/valid-groupby-query :timeout 5000))
