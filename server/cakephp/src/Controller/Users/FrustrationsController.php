<?php

/**
 * CakePHP(tm) : Rapid Development Framework (http://cakephp.org)
 * Copyright (c) Cake Software Foundation, Inc. (http://cakefoundation.org)
 *
 * Licensed under The MIT License
 * For full copyright and license information, please see the LICENSE.txt
 * Redistributions of files must retain the above copyright notice.
 *
 * @copyright Copyright (c) Cake Software Foundation, Inc. (http://cakefoundation.org)
 * @link      http://cakephp.org CakePHP(tm) Project
 * @since     0.2.9
 * @license   http://www.opensource.org/licenses/mit-license.php MIT License
 */
namespace App\Controller\Users;

use App\Controller\AppController;
use Cake\ORM\TableRegistry;

/**
 * @author shimomo0502
 */
class FrustrationsController extends AppController
{
    /**
     * @var array
     */
    public $components = ['RequestHandler'];

    /**
     * @return void
     */
    public function index()
    {
        $frustrations = $this->Frustrations->getFrustrations();
        $this->set([
            'frustrations' => $frustrations,
            '_serialize' => [
                'frustrations',
            ],
        ]);
    }

    /**
     * @return void
     */
    public function add()
    {
        $data = $this->request->input('json_decode', true)['frustrations'];
        $result = $this->Frustrations->postFrustrations($data);
        $frustrations = $this->Frustrations->getFrustrations();
        $this->set([
            'frustrations' => $frustrations,
            '_serialize' => [
                'frustrations',
            ],
        ]);
    }
}
