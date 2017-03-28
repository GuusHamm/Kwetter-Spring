/**
 * Created by guushamm on 27-3-17.
 */
export const ADD_KWEET = 'ADD_KWEET';
export const SET_VISIBILITY_FILTER = 'SET_VISIBILITY_FILTER';

export const VisibilityFilters = {
    SHOW_ALL: 'SHOW_ALL',
    SHOW_RECENT: 'SHOW_RECENT'
};

export function addKweet(message, author) {
    return { type: ADD_KWEET, message, author}
}

export function setVisibilityFilter(filter) {
    return { type: SET_VISIBILITY_FILTER, filter}
}

