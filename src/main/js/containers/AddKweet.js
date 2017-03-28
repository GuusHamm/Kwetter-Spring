/**
 * Created by guushamm on 27-3-17.
 */
import React from 'react'
import {connect} from 'react-redux'
import {addKweet} from '../actions'

let AddKweet = ({dispatch}) = {
    let input

    return (
        <div>
            <form onSubmit={e => {
                e.preventDefault()
                if (!input.value.trim()) {
                    return
                }
                dispatch(addKweet(input.value, 'test'))
                input.value = ''
            }}>
                <input ref={node => {
                    input = node
                }} />
                <button type="submit"> Add Kweet </button>
            </form>
        </div>
)
}
AddKweet = connect()(AddKweet)

export default AddKweet