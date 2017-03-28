/**
 * Created by guushamm on 27-3-17.
 */
import React, {PropTypes} from 'react'

const Kweet = ({author, message}) => (
    <li>
        {author}
        {message}
    </li>
);

Kweet.propType = {
    author: PropTypes.string.isRequired,
    message: PropTypes.string.isRequired
};

export default Kweet