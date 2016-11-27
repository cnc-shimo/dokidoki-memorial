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
        $userId = $this->request->param('user_id');
        $frustrations = $this->Frustrations->getFrustrations(['Frustrations.user_id' => $userId]);
        $total = count($frustrations->toArray());
        $this->set([
            'total' => $total,
            'frustrations' => $frustrations,
            '_serialize' => [
                'total',
                'frustrations',
            ],
        ]);
    }

    /**
     * @param  string $id
     * @return void
     */
    public function view(string $id)
    {
        $frustrations = $this->Frustrations->getFrustrations(['Frustrations.id' => $id]);
        $this->set([
            'id'            => $frustrations[0]['id'],
            'user_id'       => $frustrations[0]['user_id'],
            'couple_id'     => $frustrations[0]['couple_id'],
            'title'         => $frustrations[0]['title'],
            'message'       => $frustrations[0]['message'],
            'value'         => $frustrations[0]['value'],
            'is_read'       => $frustrations[0]['is_read'],
            'is_eliminated' => $frustrations[0]['is_eliminated'],
            'created_at'    => $frustrations[0]['created_at'],
            'updated_at'    => $frustrations[0]['updated_at'],
            '_serialize' => [
                'id',
                'user_id',
                'couple_id',
                'title',
                'message',
                'value',
                'is_read',
                'is_eliminated',
                'created_at',
                'updated_at',
            ],
        ]);
    }

    /**
     * @return void
     */
    public function add()
    {
        $userId = (integer)$this->request->param('user_id');
        $data = $this->request->input('json_decode', true)['frustrations'];
        $result = $this->Frustrations->postFrustrations($userId, $data);
        $frustrations = $this->Frustrations->getFrustrations(['Frustrations.user_id' => $userId]);
        $this->set([
            'frustrations' => $frustrations,
            '_serialize' => [
                'frustrations',
            ],
        ]);
    }
}
