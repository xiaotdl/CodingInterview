// tag: double linked list, hashmap
// time:
//   - O(1) get/put => hashmap
//   - O(1) add/remove => double linked list
// space: O(n), n is capacity
class LRUCache {
public:
    LRUCache(int capacity) : capacity_(capacity) {}
    
    int get(int key) {
        if (map_.find(key) != map_.end()) {
            // move the accessed element to the front of the list
            auto cacheItr = map_[key];
            auto value = cacheItr->second;
            // cache_.splice(cache_.begin(), cache_, cacheItr); // fancy way to doing the following 2 lines
            cache_.erase(cacheItr);
            cache_.push_front({key, value});
            map_[key] = cache_.begin();
            return value;
        }
        return -1;
    }
    
    void put(int key, int value) {
        if (map_.find(key) != map_.end()) {
            // update existing key-value pair && move the updated element to the front of the list
            auto cacheItr = map_[key];
            cacheItr->second = value;
            // cache_.splice(cache_.begin(), cache_, cacheItr); // fancy way to doing the following 2 lines
            cache_.erase(cacheItr);
            cache_.push_front({key, value});
            map_[key] = cache_.begin();
        } else {
            // evict the lru key if cache is over capacity
            if (cache_.size() >= capacity_) {
                auto lruCacheItr = cache_.back();
                map_.erase(lruCacheItr.first);
                cache_.pop_back();
            }
            // add a new key-value pair
            cache_.push_front({key, value});
            map_[key] = cache_.begin();
        }
    }

private: 
    int capacity_{0};
    std::list<std::pair<int, int>> cache_; // key value pairs, keys are in freshness order, fresh first, stale the last
    std::unordered_map<int, std::list<std::pair<int, int>>::iterator> map_; // {key : iterator}

};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
