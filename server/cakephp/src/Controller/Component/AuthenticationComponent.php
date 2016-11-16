<?php

namespace App\Controller\Component;

use Cake\Controller\Component;

/**
 * @author shimomo0502
 */
class AuthenticationComponent extends Component
{
    /**
     * @param  string $temporaryToken
     * @return boolean
     */
    public function hasTemporaryToken(string $temporaryToken)
    {
        return true;
    }

    /**
     * @param  string $accessToken
     * @return boolean
     */
    public function hasAccessToken(string $accessToken)
    {
        return true;
    }
}
