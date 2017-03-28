/**
 * Created by guushamm on 27-3-17.
 */
import { combineReducers } from 'redux';
import {ADD_KWEET, SET_VISIBILITY_FILTER, VisibilityFilters} from "./actions";
const { SHOW_ALL } = VisibilityFilters;


function kweets(state = [], action) {
    switch (action.type){
        case ADD_KWEET:
            return Object.assign({}, state, {
                kweets: [
                    ...state.kweets,
                    {
                        message: action.message,
                        author: action.author
                    }
                ]
            });
        default:
            return state
    }
}

function visibilityFilter(state = SHOW_ALL, action) {
    switch (action.type) {
        case SET_VISIBILITY_FILTER:
            return action.filter;
        default:
            return state;
    }
}

const kwetterApp = combineReducers({
    visibilityFilter,
    kweets
});

export default kwetterApp