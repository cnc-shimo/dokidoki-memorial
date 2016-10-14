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
namespace App\Controller;

use Cake\ORM\TableRegistry;

/**
 * @author shimomo0502
 */
class UsersController extends AppController
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
        $users = $this->Users->getUsers([]);
        $this->set([
            'users' => $users,
            '_serialize' => [
                'users',
            ],
        ]);
    }

    /**
     * @return void
     */
    public function view(string $id)
    {
        $user = $this->Users->getUsers(['Users.id' => $id]);
        $this->set([
            'id'                 => $user[0]['id'],
            'name'               => $user[0]['name'],
            'sex'                => $user[0]['sex'],
            'birthday'           => $user[0]['birthday'],
            'constellation'      => $user[0]['constellation'],
            'blood'              => $user[0]['blood'],
            'birthplace'         => $user[0]['birthplace'],
            'residence'          => $user[0]['residence'],
            'hobby'              => $user[0]['hobby'],
            'created_at'         => $user[0]['created_at'],
            'updated_at'         => $user[0]['updated_at'],
            'couple_id'          => $user[0]['couple_id'],
            'couple_man_id'      => $user[0]['couple_man_id'],
            'couple_woman_id'    => $user[0]['couple_woman_id'],
            'couple_created_at'  => $user[0]['couple_created_at'],
            'couple_anniversary' => $user[0]['anniversary'],
            'couple_updated_at'  => $user[0]['couple_updated_at'],
            '_serialize' => [
                'id',
                'name',
                'sex',
                'birthday',
                'constellation',
                'blood',
                'birthplace',
                'residence',
                'hobby',
                'created_at',
                'updated_at',
                'couple_id',
                'couple_man_id',
                'couple_woman_id',
                'couple_anniversary',
                'couple_created_at',
                'couple_updated_at',
            ],
        ]);
    }
}
