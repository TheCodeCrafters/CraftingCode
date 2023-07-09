#include <stdlib.h>

struct cc_allocator_sizes {
    size_t usedMemory;
    size_t maxMemory;
};

void *cc_lua_alloc(struct cc_allocator_sizes *ud, void *ptr, size_t osize, size_t nsize) {
    if (!nsize) {
        if (ptr) {
            free(ptr);
            ud->usedMemory -= osize;
        }
        return NULL;
    } else if (!ptr) {
        if (ud->usedMemory + nsize > ud->maxMemory) {
            return NULL;
        }
        ud->usedMemory += nsize;
        return malloc(nsize);
    } else {
        if (nsize == osize)
            return ptr;
        if (ud->usedMemory - osize + nsize > ud->maxMemory)
            return NULL;
        void *newPtr = realloc(ptr, nsize);
        ud->usedMemory -= osize;
        ud->usedMemory += nsize;
        return newPtr;
    }
}
