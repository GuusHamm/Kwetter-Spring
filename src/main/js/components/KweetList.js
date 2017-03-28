/**
 * Created by guushamm on 27-3-17.
 */
import React, {PropTypes} from 'react'
import Kweet from './Kweet'

const KweetList = ({kweets, onKweetClick}) => (
    <ul>
        {kweets.map(kweet =>
            <Kweet key={kweet.id}
                   {...kweet}
            />
        )}
    </ul>
);

KweetList.propTypes = {
    kweets: PropTypes.arrayOf(PropTypes.shape({
        id: PropTypes.number.isRequired,
        message: PropTypes.string.isRequired,
        author: PropTypes.string.isRequired
    }).isRequired)
};

export default KweetList