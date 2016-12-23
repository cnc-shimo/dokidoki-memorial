<?php

namespace App\Model\Table;

use Cake\Database\Expression\IdentifierExpression;
use Cake\I18n\Time;
use Cake\ORM\Table;
use DateTimeImmutable;

/**
 * @author shimomo0502
 */
class FrustrationsTable extends Table
{
    /**
     * @param  array $conditions
     * @return array
     */
    public function getFrustrations(array $conditions = [])
    {
        $frustrations = $this->find()->select([
            'id'            => 'Frustrations.id',
            'user_id'       => 'Frustrations.user_id',
            'couple_id'     => 'Frustrations.couple_id',
            'title'         => 'Frustrations.title',
            'message'       => 'Frustrations.message',
            'value'         => 'Frustrations.value',
            'is_read'       => 'Frustrations.is_read',
            'is_eliminated' => 'Frustrations.is_eliminated',
            'created_at'    => 'Frustrations.created_at',
            'updated_at'    => 'Frustrations.updated_at',
        ]);

        foreach ($conditions as $key => $value) {
            $frustrations->where([$key => $value]);
        }

        return json_decode(json_encode($frustrations->all()), true);
    }

    /**
     * @param  int   $userId
     * @param  array $data
     * @return boolean
     */
    public function postFrustrations(int $userId, array $data)
    {
        $query = $this->query()->insert([
            'user_id',
            'couple_id',
            'title',
            'message',
            'value',
            'is_read',
            'is_eliminated',
            'created_at',
            'updated_at',
        ]);

        $coupleId     = 1;
        $isRead       = false;
        $isEliminated = false;
        $createdAt    = Time::now();

        foreach ($data as $d) {
            $d['user_id']       = $userId;
            $d['couple_id']     = $coupleId;
            $d['is_read']       = $isRead;
            $d['is_eliminated'] = $isEliminated;
            $d['created_at']    = $createdAt;
            $d['updated_at']    = $createdAt;
            $query->values($d);
        }

        $query->execute();
        return true;
    }
}
